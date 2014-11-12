package domain

import org.uqbar.commons.utils.Observable
import org.joda.time.DateTime

@Observable
class Pedido(unPuesto:PuestoDeVenta, unCliente: Cliente, unTipoDePago:TipoDePago, unaFecha: DateTime= new DateTime("2013-12-01")){
	
	var _entradas:List[Entrada]= List.empty[Entrada] 	
	var _tipoPago:TipoDePago = unTipoDePago;
	var _cliente: Cliente = unCliente;
	var _fechaDeCompra: DateTime  = unaFecha
	var _puestoDeVenta : PuestoDeVenta = unPuesto

	

		
  def agregarEntradaComun(unTipoCliente: TipoCliente, unaNoche: Noche, unaButaca: Butaca, elCodigo: String=""): Boolean = {
      if (unaButaca.codigo.!=(elCodigo)){return false;}
	  var entradaComun = new EntradaComun( _puestoDeVenta, _cliente, unTipoCliente, unaNoche, unaButaca, _fechaDeCompra)
	  
	  //La asignacion del nro de factura podria estar en el constructor de entrada. FIXME
	  
	  _entradas=_entradas.+:(entradaComun);
	  return true;
	}
  
  

  def agregarEntradaVip(unTipoCliente: TipoCliente, unaButaca: Butaca,unFestival:Festival ,elCodigo: String=""): Boolean = {
    for(noche <- unFestival.noches){
    	if (!noche.butacasLibres.contains(unaButaca)) {return false;} 
    }  
    if (unaButaca.codigo.!=(elCodigo)){return false;}
	var entradaVip = new EntradaVIP(_puestoDeVenta,_cliente, unTipoCliente, unaButaca,_fechaDeCompra,unFestival);
		entradaVip.nroFactura = NroFactura.SacarNroFactura;
	  _entradas=_entradas.+:(entradaVip);
	  return true;
  }
    
  def precioAcumulado():Double = {
    var total=0.0;
    for (entrada <- _entradas){
      total= total + entrada.precioDeVenta;
    }
    return total;
  }
  
  def precioNeto():Double = {
    var acu=this.precioAcumulado();
    if (acu>=1000.0){ acu= acu*0.9}
    return acu;
  }
  
  def comprar() {
   for (entrada <- _entradas){
     _tipoPago.comprar(entrada)
     
   }  
    
  }
  
}