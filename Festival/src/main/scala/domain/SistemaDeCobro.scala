package domain

trait SistemaDeCobro {

  def cobrar(precioDeVenta:Double, nombreCliente:String, numeroTarjeta:String)
  def estaConectado()
  def validar()
}