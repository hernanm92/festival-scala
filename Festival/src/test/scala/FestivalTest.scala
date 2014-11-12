	
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.Assert._
import java.lang.Object
import domain._
import org.joda.time._
import org.joda.convert._


class FestivalTest {

  @Before
  var descuentos = List[String]("mujeres", "menores de 18", "menores de 12", "jubilados");

  var estadio = new PuestoDeVenta("Estadio Velez");
  var abasto = new PuestoDeVenta("Abasto");
  var dot = new PuestoDeVenta("DOT");
  var unicenter = new PuestoDeVenta("Unicenter");
  var kiosko = new PuestoDeVenta("Kiosko Tito");
  
  
  
  
  var noviembreFest:Festival  = new Festival("noviembreFest")
  noviembreFest.descuentos = descuentos
  noviembreFest.pagosPendientes = List.empty[Pago]
  noviembreFest.entradasVendidas = List.empty[Entrada]
  
  var diciembreFest:Festival = new Festival("diciembreFest")
  diciembreFest.descuentos = descuentos
  diciembreFest.pagosPendientes = List.empty[Pago]
  diciembreFest.entradasVendidas = List.empty[Entrada]

  var sectorA = new Sector(80.0, 'A');
  var sectorB = new Sector(60.0, 'B');
  var sectorC = new Sector(40.0, 'C');

  var fila1 = new Fila(1, 50.0);
  var fila2 = new Fila(2, 40.0);
  var fila3 = new Fila(3, 30.0);

  var pagoTarjeta = new PagoConTarjeta();
  var pagoEfectivo = new PagoEnEfectivo();

  var butaca1_1A = new Butaca(sectorA, fila1, 1);
  var butaca2_2A = new Butaca(sectorA, fila2, 2);
  var butaca3_3A = new Butaca(sectorA, fila3, 3);
  var butaca4_1B = new Butaca(sectorB, fila1, 4);
  var butaca5_2B = new Butaca(sectorB, fila2, 5);
  var butaca6_3B = new Butaca(sectorB, fila3, 6);
  var butaca7_1C = new Butaca(sectorC, fila1, 7);
  var butaca8_2C = new Butaca(sectorC, fila2, 8);
  var butaca9_3C = new Butaca(sectorC, fila3, 9);
  var butaca10_3C = new Butaca(sectorC, fila3, 10);
  var butaca11_3C = new Butaca(sectorC, fila3, 11);
  var butaca12_3C = new Butaca(sectorC, fila3, 12);
  var butaca13_3C = new Butaca(sectorC, fila3, 13);
  var butaca14_3C = new Butaca(sectorC, fila3, 14);
  var butaca15_3C = new Butaca(sectorC, fila3, 15);
  var butaca16_3C = new Butaca(sectorC, fila3, 16);
  var butaca17_3C = new Butaca(sectorC, fila3, 17);
  var butaca18_3C = new Butaca(sectorC, fila3, 18);
  var butaca19_3C = new Butaca(sectorC, fila3, 19);
  var butaca20_3C = new Butaca(sectorC, fila3, 20);
  var butaca21_3C = new Butaca(sectorC, fila3, 21);

  var butaca22_3C = new Butaca(sectorC, fila3, 22);
  var butaca23_3C = new Butaca(sectorC, fila3, 23);
  var butaca24_3C = new Butaca(sectorC, fila3, 24);
  var butaca25_3C = new Butaca(sectorC, fila3, 25);
  var butaca26_3C = new Butaca(sectorC, fila3, 26);
  var butaca27_3C = new Butaca(sectorC, fila3, 27);
  var butaca28_3C = new Butaca(sectorC, fila3, 28);
  var butaca29_3C = new Butaca(sectorC, fila3, 29);
  var butaca30_3C = new Butaca(sectorC, fila3, 30);

  var todasLasButacas = List[Butaca](butaca1_1A, butaca2_2A,
    butaca3_3A, butaca4_1B, butaca5_2B, butaca6_3B, butaca7_1C,
    butaca8_2C, butaca9_3C, butaca10_3C, butaca11_3C, butaca13_3C, butaca14_3C,
    butaca12_3C, butaca15_3C, butaca16_3C, butaca17_3C, butaca18_3C,
    butaca19_3C, butaca20_3C, butaca21_3C, butaca22_3C, butaca23_3C, butaca24_3C, butaca25_3C, butaca26_3C,
    butaca27_3C, butaca28_3C, butaca29_3C, butaca30_3C);

  var categoria1 = new Categoria(1, 00.0);
  var categoria2 = new Categoria(2, 50.0);
  var categoria3 = new Categoria(3, 100.0);
  var categoria4 = new Categoria(4, 200.0);

  var losPiojos = new Banda(categoria3, "Los Piojos");
  var arbol = new Banda(categoria2, "Arbol");
  var laVelaPuerca = new Banda(categoria2, "La Vela Puerca");
  var NTVG = new Banda(categoria1, "NTVG");
  var callejeros = new Banda(categoria2, "Callejeros");
  var losAutentiosDecadentes = new Banda(categoria3, "Los Autenticos Decadentes");
  var rollingStone = new Banda(categoria4, "Los Rolling Stone");

  var noche1 = new Noche(noviembreFest,new DateTime("2013-12-20"), 20, 1, List[Banda](losPiojos), todasLasButacas);
  var noche2 = new Noche(noviembreFest,new DateTime("2013-12-21"), 20, 2, List[Banda](arbol), todasLasButacas);
  var noche3 = new Noche(noviembreFest,new DateTime("2013-12-22"), 19, 3, List[Banda](laVelaPuerca, NTVG), todasLasButacas);
  var noche4 = new Noche(noviembreFest,new DateTime("2013-12-23"), 19, 4, List[Banda](callejeros, rollingStone), todasLasButacas);
  var noche5 = new Noche(noviembreFest,new DateTime("2013-12-24"), 21, 5, List[Banda](losAutentiosDecadentes), todasLasButacas);
  noviembreFest.noches = List[Noche](noche1, noche2, noche3, noche4, noche5);
  
  var nocheUno = new Noche(diciembreFest,new DateTime("2013-12-26"), 20, 1, List[Banda](losPiojos), todasLasButacas);
  var nocheDos = new Noche(diciembreFest,new DateTime("2013-12-27"), 20, 2, List[Banda](arbol), todasLasButacas);
  var nocheTres = new Noche(diciembreFest,new DateTime("2013-12-28"), 19, 3, List[Banda](laVelaPuerca, NTVG), todasLasButacas);
  var nocheCuatro = new Noche(diciembreFest,new DateTime("2013-12-29"), 19, 4, List[Banda](callejeros, rollingStone), todasLasButacas);
  var nocheCinco = new Noche(diciembreFest,new DateTime("2013-12-30"), 21, 5, List[Banda](losAutentiosDecadentes), todasLasButacas);
  diciembreFest.noches = List[Noche](noche1, noche2, noche3, noche4, noche5);

  /*
 	 * Carlos es mayor
 	 * Jose es jubilado
 	 * facundo es menor
 	 * pablo es menor
 	 * pedrito es menor acompaÃ±ado
 	 * florencia es mujer
 	 */

  var clienteMayor = new TipoCliente_Mayor("Cliente Mayor");
  var clienteMujer = new TipoCliente_Mujer("Cliente Mujer");
  var clienteMenor = new TipoCliente_Menor("Cliente Menor");
  var clienteJubilado = new TipoCliente_Jubilado("Cliente Jubilado");
  var clienteMenor12Acompaniado = new TipoCliente_MenorDe12Acompaniado("Cliente Menor de 12 Acompaniado");
  var clienteMenor12NoAcompaniado = new TipoCliente_MenorDe12NoAcompaniado("Cliente Menor de 12 No Acompaniado");

  var carlos = new Cliente("Carlos", "Fernandez", "5432524545", "|40 anios|DNI = 17.897.364");
  var jose = new Cliente("Josa", "Gomez", "123324545", "|60 anios|DNI = 8.897.364");
  var facundo = new Cliente("Facundo", "Hernandez", "862466754", "|15 anios|DNI = 42.897.364");
  var pablo = new Cliente("Pablo", "Gimenez", "543252654", "|20 anios|DNI = 37.897.364");
  var pedrito = new Cliente("Pedrito", "Benitez", "4532524545", "|11 anios|DNI = 47.897.364");
  var florencia = new Cliente("Florencia", "Rodriguez", "734624545", "|19 anios|DNI = 39.897.364");

  @Test
  def cantidadDeButacasLibresTodasLasNoches() {
    assertEquals(noche1.butacasLibres.size, 30);
    assertEquals(noche2.butacasLibres.size, 30);
    assertEquals(noche3.butacasLibres.size, 30);
    assertEquals(noche4.butacasLibres.size, 30);
    assertEquals(noche5.butacasLibres.size, 30);
  }

  @Test
  def cantidadDeBandasPorNoche() {
    assertEquals(noche1.bandas.size, 1);
    assertEquals(noche2.bandas.size, 1);
    assertEquals(noche3.bandas.size, 2);
    assertEquals(noche4.bandas.size, 2);
    assertEquals(noche5.bandas.size, 1);
  }

  @Test
  def maximasCategoriasPorNoche() {
    assertEquals(noche1.maxCategoria.nivel, 3);
    assertEquals(noche2.maxCategoria.nivel, 2);
    assertEquals(noche3.maxCategoria.nivel, 2);
    assertEquals(noche4.maxCategoria.nivel, 4);
    assertEquals(noche5.maxCategoria.nivel, 3);
  }

  @Test
  def valorExtraPorNoche() {
    assertEquals(100.0, noche1.maxCategoria.costoExtra, 0.0);
    assertEquals(50.0, noche2.maxCategoria.costoExtra, 0.0);
    assertEquals(50.0, noche3.maxCategoria.costoExtra, 0.0);
    assertEquals(200.0, noche4.maxCategoria.costoExtra, 0.0);
    assertEquals(100.0, noche5.maxCategoria.costoExtra, 0.0);
  }

  @Test
  def valorEntradaBase() {
    assertEquals(130.0, butaca1_1A.precioBase, 0.0);
    assertEquals(110.0, butaca4_1B.precioBase, 0.0);
    assertEquals(80.0, butaca8_2C.precioBase, 0.0);
    assertEquals(90.0, butaca6_3B.precioBase, 0.0);
    assertEquals(110.0, butaca3_3A.precioBase, 0.0);
    assertEquals(90.0, butaca7_1C.precioBase, 0.0);
  }

  @Test
  def compraDeEntradaClienteMayorNoche1Butaca1() {
    var pedido = new Pedido(estadio,carlos, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca1_1A);
    pedido.comprar();

    assertEquals(230.0, pedido.precioNeto(), 0.0);
  }
  @Test
  def compraDeEntradaClienteJubiladoNoche2Butaca6() {
    var pedido = new Pedido(estadio,jose, pagoEfectivo);
    pedido.agregarEntradaComun(clienteJubilado, noche2, butaca6_3B);
    pedido.comprar();

    assertEquals(90.0 * 0.85 + 50.0, pedido.precioNeto(), 0.0);
  }

  @Test
  def compraDeEntrada_Mayor100_ClienteMenor18Noche3Butaca4() {
    var pedido = new Pedido(abasto,facundo, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMenor, noche3, butaca4_1B);
    pedido.comprar();

    assertEquals(110.0 - 22.0 + 50.0, pedido.precioNeto(), 0.0);
  }

  @Test
  def compraDeEntrada_Menor100_ClienteMenor18Noche4Butaca9() {
    var pedido = new Pedido(dot,pablo, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMenor, noche4, butaca9_3C);
    pedido.comprar();

    assertEquals(70.0 - 10.0 + 200.0, pedido.precioNeto(), 0.0);
  }

  //EN los siguientes Tests, hay problemas con las diferencias de fechas,
  //No lo pude arreglar. El segundo da VERDE, pero el primero no pasa

  @Test
  def compraDeEntradaAnticipada_ClienteMayorNoche5Butaca5() {
     var pedido = new Pedido(kiosko,carlos,pagoEfectivo,new DateTime("2013-11-09"));
     pedido.agregarEntradaComun(clienteMayor,noche5, butaca5_2B);
     pedido.comprar();
     
     assertEquals(100.0 - 20.0 + 100.0, pedido._entradas.head.precioDeVenta, 0.0);
  }

  @Test
  def comprarYDevolverEntrada() {
    
    var fechaDevolucion = new DateTime("2013-12-05")
    var pedido = new Pedido(unicenter,carlos, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca3_3A);
    pedido.comprar();

    assertEquals(210.0, pedido._entradas.head.precioDeVenta, 0.0);
    var precioDevuelto = pedido._entradas.head.devolver(fechaDevolucion)
    
    assertEquals(105.0, precioDevuelto, 0.0);
  }
	
  /***************** PUNTO 1 - Venta en Combo ***********/

  @Test
  def comprarComboDeEntradasSin10DeDescuento {
    var pedido = new Pedido(dot,carlos, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca2_2A);
    pedido.agregarEntradaComun(clienteMayor, noche2, butaca8_2C);
    pedido.agregarEntradaComun(clienteMayor, noche3, butaca7_1C);
    pedido.comprar();

    assertEquals(490.0, pedido.precioNeto(), 0.0);

  }

  @Test
  def comprarComboDeEntradasCon10DeDescuento {
    var pedido = new Pedido(dot,carlos, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca10_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca11_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca12_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca13_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca14_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca15_3C);
    pedido.agregarEntradaComun(clienteMayor, noche1, butaca16_3C);
    pedido.comprar();

    assertEquals(1190.0 - 119, pedido.precioNeto(), 0.0);
  }

  /***************** PUNTO 5 - Entradas VIP ************/
  
  /*
   * Mando el festival por parametro porque sino no puedo conocer las noches, buscar otra forma, TODO
   */
  @Test
  def compraDeEntradaVIP_ClienteMayorButaca17() {
    var pedido = new Pedido(kiosko,carlos, pagoEfectivo);
    pedido.agregarEntradaVip(clienteMayor, butaca17_3C,noviembreFest);
    pedido.comprar();
    assertEquals(1275.0, pedido.precioAcumulado(), 0.0);
    //Si tenemos en cuenta que es mayor a 1000 y debemos aplicar el descuento queda menos dinero
    assertEquals(1147.5, pedido.precioNeto(), 0.0)
  }
 

  /***************** PUNTO 3 - Categorias De Bandas  ************/
  @Test
  def cambiarCategoriaACallejeros() {
    assertEquals(50.0, callejeros.categoria.costoExtra, 0.0);
    callejeros.categoria(categoria4);
    assertEquals(200.0, callejeros.categoria.costoExtra, 0.0);
  }

  /***************** PUNTO 2 - Cmabios en la politica de descuento  ************/
  @Test
  def compraDeEntrada_ClienteMenorDe12Noche4Butaca18() {
    var pedido = new Pedido(dot,pedrito, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMenor12Acompaniado, noche4, butaca18_3C);
    pedido.comprar();
    assertEquals(70.0 - 35.0 + 200.0, pedido.precioNeto(), 0.0);
  }

  @Test
  def compraDeEntrada_ClienteMujerNoche3Butaca19() {
    var pedido = new Pedido(estadio,florencia, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMujer, noche3, butaca19_3C);
    pedido.comprar();
    assertEquals(70.0 - 14.0 + 50.0, pedido.precioNeto(), 0.0);
  }

  @Test
  def compraDeEntrada_ClienteMujerNoche1Butaca20_SinDescuento() {
    //Para que el festival no tenga el descuento de mujeres, lo saco e la lista de descuentos
    //Y despues lo vuelvo a meter para que no haya problemas con los otros tests

    noviembreFest.sacarDescuento("mujeres");
    var pedido = new Pedido(estadio,florencia, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMujer, noche1, butaca20_3C);
    pedido.comprar();
    assertEquals(70.0 - 0.0 + 100.0, pedido.precioNeto(), 0.0);
    noviembreFest.agregarDescuento("mujeres");
  }

  def compraDeEntrada_ClienteJubiladoNoche4Butaca21_SinDescuento() {
    //Para que el festival no tenga el descuento de mujeres, lo saco e la lista de descuentos
    //Y despues lo vuelvo a meter para que no haya problema	s con los otros tests
    noviembreFest.sacarDescuento("jubilados");
    var pedido = new Pedido(dot,jose, pagoEfectivo);
    pedido.agregarEntradaComun(clienteJubilado, noche4, butaca21_3C);
    pedido.comprar();
    assertEquals(70.0 - 0.0 + 200.0, pedido.precioNeto(), 0.0);
    noviembreFest.agregarDescuento("jubilados");
  }

  /*Hay que cambiar estos tests porque ya no podemos acceder a la entrada para ver si esta contenida en el sistema*/
  @Test
  def compraDeEntradaReservada_ClienteJubiladoNoche2Butaca6() {
    noche2.reservarButaca(butaca6_3B, "Codigo_de_Prueba");
    var pedido = new Pedido(dot,jose, pagoEfectivo);
    pedido.agregarEntradaComun(clienteJubilado, noche4, butaca6_3B);
    assert(pedido._entradas.isEmpty);

    pedido = new Pedido(unicenter,jose, pagoEfectivo);
    pedido.agregarEntradaComun(clienteJubilado, noche4, butaca6_3B, "");
    assert(pedido._entradas.isEmpty);

    pedido.agregarEntradaComun(clienteJubilado, noche4, butaca6_3B, "Codigo_de_Prueba");
    pedido.comprar();
    assert(noviembreFest.entradasVendidas.contains(pedido._entradas.head));
    assert(!noche2.butacasLibres.contains(pedido._entradas.head.butaca));
  }

  /***************** PUNTO 6 - Compra con tarjeta  ************/

  @Test
  def impostorEstaConectadoYValida {
    var unImpostorValidaYConecta = new ImpostorSistemaDeCobro(true, true);
    pagoTarjeta._sisCobro = unImpostorValidaYConecta;
    var pedidoValidaYConecta = new Pedido(unicenter,florencia, pagoTarjeta);
    pedidoValidaYConecta.agregarEntradaComun(clienteMujer, noche1, butaca20_3C)

    pedidoValidaYConecta.comprar();

    assert(noviembreFest.entradasVendidas.contains(pedidoValidaYConecta._entradas.head));
    assert(!noche1.butacasLibres.contains(pedidoValidaYConecta._entradas.head.butaca))
  }

  @Test
  def impostorEstaConectadoYNoValida {
    var unImpostorNoValidaYConecta = new ImpostorSistemaDeCobro(true, false);
    pagoTarjeta._sisCobro = unImpostorNoValidaYConecta;
    var pedidoNoValidaYConecta = new Pedido(dot,florencia, pagoTarjeta);
    pedidoNoValidaYConecta.agregarEntradaComun(clienteMujer, noche1, butaca20_3C)

    pedidoNoValidaYConecta.comprar();

    assert(!noviembreFest.entradasVendidas.contains(pedidoNoValidaYConecta._entradas.head));
    assert(noche1.butacasLibres.contains(pedidoNoValidaYConecta._entradas.head.butaca))
  }

  @Test
  def impostorEstaNoConectadoYValida {
    var unImpostorValidaYNoConecta = new ImpostorSistemaDeCobro(false, true);
    pagoTarjeta._sisCobro = unImpostorValidaYNoConecta;
    var pedidoValidaYNoConecta = new Pedido(dot,florencia, pagoTarjeta);
    pedidoValidaYNoConecta.agregarEntradaComun(clienteMujer, noche1, butaca20_3C)

    pedidoValidaYNoConecta.comprar();

    assert(!noviembreFest.entradasVendidas.contains(pedidoValidaYNoConecta._entradas.head));
    assert(!noche1.butacasLibres.contains(pedidoValidaYNoConecta._entradas.head.butaca))

    //Si el usuario intenta pagar pero el servicio esta desconectado, se pone en un
    //lista de pendientes pero la entrada figura como comprada, esto es porque si luego
    //se cobra correctamente la entrada deberia seguir vendida

    assertEquals(1, noviembreFest.pagosPendientes.length);
  }

  @Test
  def impostorEstaNoConectadoYNoValida {
    var unImpostorNoValidaYNoConecta = new ImpostorSistemaDeCobro(false, false);
    pagoTarjeta._sisCobro = unImpostorNoValidaYNoConecta;
    var pedidoNoValidaYNoConecta = new Pedido(dot,florencia, pagoTarjeta);
    pedidoNoValidaYNoConecta.agregarEntradaComun(clienteMujer, noche1, butaca20_3C)

    pedidoNoValidaYNoConecta.comprar();

    assert(!noviembreFest.entradasVendidas.contains(pedidoNoValidaYNoConecta._entradas.head));
    assert(!noche1.butacasLibres.contains(pedidoNoValidaYNoConecta._entradas.head.butaca))

    assertEquals(1, noviembreFest.pagosPendientes.length);
  }
  
  
  /**
   * Tests para un festival nuevo
 **/
    @Test
  def compraDeEntrada_ClienteMujerNoche3Butaca19DiciembreFest() {
    var pedido = new Pedido(dot,florencia, pagoEfectivo);
    pedido.agregarEntradaComun(clienteMujer, nocheTres, butaca19_3C);
    pedido.comprar();
    assertEquals(70.0 - 14.0 + 50.0, pedido.precioNeto(), 0.0);
  }
  
  
  
  
}


