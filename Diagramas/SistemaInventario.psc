Algoritmo SistemaInventario
	Definir opcion Como Entero
	Repetir
		Escribir '----- SISTEMA INVENTARIO -----'
		Escribir '1. Agregar producto'
		Escribir '2. Buscar producto'
		Escribir '3. Eliminar producto'
		Escribir '4. Registrar venta'
		Escribir '5. Generar reporte'
		Escribir '6. Datos estudiante'
		Escribir '7. Salir'
		Leer opcion
		Según opcion Hacer
			1:
				Escribir 'Agregar producto'
			2:
				Escribir 'Buscar producto'
			3:
				Escribir 'Eliminar producto'
			4:
				Escribir 'Registrar venta'
			5:
				Escribir 'Generar reporte'
			6:
				Escribir 'Datos estudiante'
		FinSegún
	Hasta Que opcion=7
FinAlgoritmo
