package com.example.demo.dto;

public record ProductoRequest(

	String nombre,
	String stock,
	String precio,
	String tipo,
	String fecha_pro
) {
}
