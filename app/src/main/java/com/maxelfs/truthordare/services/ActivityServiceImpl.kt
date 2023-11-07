package com.maxelfs.truthordare.services

import com.maxelfs.truthordare.interfaces.*
import com.maxelfs.truthordare.models.*
import javax.inject.Inject

class ActivityServiceImpl @Inject constructor(
    private val _activityRepository: ActivityRepository,
    private val _textFormatter: ActivityTextFormatter,
    private val _playerService: PlayerService,
    private val _packService: ActivityPackService
) : ActivityService {
    private val _activities = mutableListOf<ActivityWithIncludes>()
    private val _queues = mutableMapOf<ActivityType, Map<Gender, ArrayDeque<ActivityWithIncludes>>>()


    override suspend fun reloadActivitiesAsync() : Boolean {
        val currentPackId = _packService.getSelectedPackId()
        if (currentPackId == 0) {
            return false
        }

        val activities = _activityRepository.getActivitiesWithTranslationsAsync(currentPackId)
        _activities.clear()
        _activities.addAll(activities)

        for (type in ActivityType.values()) {
            val genderActivities = mutableMapOf<Gender, ArrayDeque<ActivityWithIncludes>>()
            for (gender in Gender.values()) {
                genderActivities[gender] = ArrayDeque()
            }
            _queues[type] = genderActivities
        }

        return true
    }

    override suspend fun getNextActivityTextAsync(activityType: ActivityType, player: Player) : String? {
        val activityQueue = _queues[activityType]!![player.gender]!!
        val activity = getNextActivityAsync(activityType, player.gender, activityQueue)
            ?: return null

        return _textFormatter.getFormattedText(activity, player)
            ?: getNextActivityTextAsync(activityType, player)
    }


    private suspend fun getNextActivityAsync(
        activityType: ActivityType,
        currentPlayerGender: Gender,
        queue: ArrayDeque<ActivityWithIncludes>) : ActivityWithIncludes? {
        if (queue.isNotEmpty()) {
            return queue.removeFirst()
        }

        if (_activities.isEmpty()) {
            reloadActivitiesAsync()
        }

        var activities = _activities.filter {
            it.activity.type == activityType &&
            (it.activity.gender == currentPlayerGender || it.activity.gender == Gender.NOT_SET)
        }

        val players = _playerService.getPlayers()
        // filter activity in case there's no one to act in variable except the current player
        val playersWithThisGenderCount = players.count { it.gender == currentPlayerGender }
        if (playersWithThisGenderCount <= 1) {
            activities = activities.filter { a ->
                a.variables.all {
                    it.type != ActivityVariableType.PLAYER || it.gender!! != currentPlayerGender
                }
            }
        }
        // filter activities in case there's no one with the gender opposite to current player's one
        val oppositeGenderPlayersCount = players.count { it.gender != currentPlayerGender }
        if (oppositeGenderPlayersCount == 0) {
            activities = activities.filter { a ->
                a.variables.all {
                    it.type != ActivityVariableType.OPPOSITE_GENDER_PLAYER
                }
            }
        }

        activities = activities.shuffled()
        queue.addAll(activities)

        if (queue.isEmpty()) {
            return null
        }

        return queue.removeFirst()
    }
}