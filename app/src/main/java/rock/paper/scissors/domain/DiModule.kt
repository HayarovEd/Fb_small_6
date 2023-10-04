package rock.paper.scissors.domain

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rock.paper.scissors.data.KeeperImpl
import rock.paper.scissors.data.ServiceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DiModule {
    @Binds
    @Singleton
    abstract fun bindService(serviceImpl: ServiceImpl): Service

    @Binds
    @Singleton
    abstract fun bindKeeper(keeperImpl: KeeperImpl): Keeper
}