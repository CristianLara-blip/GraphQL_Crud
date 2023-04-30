package com.example.demo.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.example.demo.dto.ProductoRequest;
import com.example.demo.entity.Producto;
import com.example.demo.repository.ProductoRepository;
@Controller
public class ProductoController {
	
	@Autowired
	private ProductoRepository prod;
	
	@QueryMapping
	public List<Producto> listarProductos(){
		return prod.findAll();
	}
	
	
	@QueryMapping
	public Producto listarProductoPorId(@Argument Long id) {
		return prod.findById(id).orElseThrow(
				() -> new RuntimeException(String.format("Producto %s no encontrado", id))
				);
	}
	
	@MutationMapping
	public Producto guardarProducto(@Argument Producto producto) {
		producto.setId_producto(UUID.randomUUID().toString());
		
		return prod.save(producto);
		
	}
	
	@MutationMapping
	public Producto editarProducto(@Argument String id, @Argument ProductoRequest productoRequest) {
		Producto productoBBDD = new Producto();
		productoBBDD.setId_producto(id);
		productoBBDD.setNombre(productoRequest.nombre());
		productoBBDD.setStock(productoRequest.stock());
		productoBBDD.setPrecio(productoRequest.precio());
		productoBBDD.setTipo(productoRequest.tipo());
		productoBBDD.setFecha_pro(productoRequest.fecha_pro());
		
		return prod.save(productoBBDD);
	}
	
	@MutationMapping
	public Producto eliminarProducto(@Argument Long id) {
		prod.deleteById(id);
		return null;
		
	}
}
