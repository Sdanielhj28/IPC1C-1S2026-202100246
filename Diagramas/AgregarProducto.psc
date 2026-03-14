Algoritmo AgregarProducto
	Definir codigo Como Cadena
	Definir nombre Como Cadena
	Definir categoria Como Cadena
	Definir precio Como Real
	Definir cantidad Como Entero
	Escribir 'Ingrese codigo del producto'
	Leer codigo
	Escribir 'Ingrese nombre del producto'
	Leer nombre
	Escribir 'Ingrese categoria'
	Leer categoria
	Escribir 'Ingrese precio'
	Leer precio
	Si precio<0 Entonces
		Escribir 'Error: precio invalido'
	SiNo
		Escribir 'Ingrese cantidad'
		Leer cantidad
		Si cantidad<0 Entonces
			Escribir 'Error: cantidad invalida'
		SiNo
			Escribir 'Producto agregado correctamente'
		FinSi
	FinSi
FinAlgoritmo
