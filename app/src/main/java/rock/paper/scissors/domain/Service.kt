package rock.paper.scissors.domain

interface Service {
    fun vpnActive(): Boolean
    fun batteryLevel(): Int
    fun checkIsEmu(): Boolean
}