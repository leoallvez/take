package io.github.leoallvez.take.data.api.repository.discovery

import io.github.leoallvez.take.data.model.ListSetup
import io.github.leoallvez.take.di.AbListSetup
import io.github.leoallvez.take.di.IoDispatcher
import io.github.leoallvez.take.experiment.AbExperiment
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DiscoveryRepository @Inject constructor(
    private val dataSource: DiscoveryDataSource,
    @AbListSetup
    private val experiment: AbExperiment<List<ListSetup>>,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) {

    suspend fun getListsSetup(): List<ListSetup> {
        return withContext(ioDispatcher) {
            val setups = experiment.execute()
            setups.forEach { setup ->
                /**
                HOME
                1 - Verificar se tem cache.

                2 - Se tem tem cache ->
                    a) Buscar da base de dados os valores.
                    b) Montar a estrutura de retorno.

                SPLASH SCREEN
                3 - Senão tem cache
                    a) Busca valores na rede.
                    b) Montar a estrutura de retorno.
                    c) Salvar cache.

                SERVIÇO EM BACKGROUND
                4 - o app deve deletar os cache uma única vez por dia.
                */
            }

            setups
        }
    }
}