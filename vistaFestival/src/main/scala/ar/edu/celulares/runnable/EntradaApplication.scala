package ar.edu.celulares.runnable

import ar.edu.celulares.ui.MostradorEntradaWindow
import org.uqbar.arena.Application
import ar.edu.celulares.ui.MenuPrincipalWindow

object EntradaApplication  extends Application with App{
  override def createMainWindow() = new MenuPrincipalWindow(this)
//  override def createMainWindow() =  new MostradorEntradaWindow(this)
  start();

}