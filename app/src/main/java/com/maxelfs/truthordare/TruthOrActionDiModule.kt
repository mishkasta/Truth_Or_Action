package com.maxelfs.truthordare

import android.content.Context
import androidx.room.Room
import com.maxelfs.truthordare.data.*
import com.maxelfs.truthordare.interfaces.*
import com.maxelfs.truthordare.services.*
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [TruthOrActionDiModule.ProvideModule::class])
@InstallIn(SingletonComponent::class)
abstract class TruthOrActionDiModule {
    @Binds
    @Singleton
    abstract fun bindActivityRepository(
        activityRepositoryImpl: ActivityRepositoryImpl) : ActivityRepository
    @Binds
    @Singleton
    abstract fun bindActivityPackRepository(
        activityPackRepositoryImpl: ActivityPackRepositoryImpl) : ActivityPackRepository

    @Binds
    @Singleton
    abstract fun bindActivityService(activityServiceImpl: ActivityServiceImpl) : ActivityService

    @Binds
    @Singleton
    abstract fun bindPackService(packServiceImpl: ActivityPackServiceImpl) : ActivityPackService

    @Binds
    @Singleton
    abstract fun bindPlayerService(playerServiceImpl: PlayerServiceImpl) : PlayerService

    @Binds
    @Singleton
    abstract fun bindAdService(adServiceImpl: AdServiceImpl) : AdService

    @Binds
    @Singleton
    abstract fun bindRateService(rateServiceImpl: RateServiceImpl) : RateService

    @Binds
    @Singleton
    abstract fun bindActivityTextFormatter(activityTextFormatterImpl: ActivityTextFormatterImpl)
    : ActivityTextFormatter

    @Binds
    @Singleton
    abstract fun bindInsertVariableStrategyProvider(
        providerImpl: InsertVariableStrategyProviderImpl) : InsertVariableStrategyProvider

    @Binds
    @Singleton
    abstract fun bindTooltipService(tooltipServiceImpl: TooltipServiceImpl) : TooltipService

    @Binds
    @Singleton
    abstract fun bindActivityProvider(activityProviderImpl: ActivityProviderImpl) : ActivityProvider

    @Binds
    @Singleton
    abstract fun bindKeyboardService(
        keyboardServiceImpl: SoftKeyboardServiceImpl) : SoftKeyboardService


    @Module
    @InstallIn(SingletonComponent::class)
    internal object ProvideModule {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext appContext: Context)
                : TruthOrActionDatabase {
            return Room.databaseBuilder(
                appContext,
                TruthOrActionDatabase::class.java,
                "TruthOrAction")
                .fallbackToDestructiveMigration()
                .createFromAsset("seed.db")
                .build()
        }

        @Provides
        fun provideActivityDao(database: TruthOrActionDatabase) : ActivityDao {
            return database.activityDao
        }

        @Provides
        fun providePackDao(database: TruthOrActionDatabase) : ActivityPackDao {
            return database.packDao
        }

        @Provides
        @Singleton
        fun provideAppSettingsService(@ApplicationContext context: Context) : AppSettingsService {
            return AppSettingsServiceImpl(context)
        }
    }
}