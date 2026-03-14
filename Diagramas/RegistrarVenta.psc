Algoritmo RegistrarVenta
	Definir codigo Como Cadena
	Definir cantidad Como Entero
	Definir stock Como Entero
	Escribir 'Ingrese codigo del producto'
	Leer codigo
	Escribir 'Ingrese cantidad a vender'
	Leer cantidad
	stock <- 10
	Si cantidad<=stock Entonces
		stock <- stock-cantidad
		Escribir 'Venta registrada'
	SiNo
		Escribir 'No hay suficiente stock'
	FinSi
FinAlgoritmo
