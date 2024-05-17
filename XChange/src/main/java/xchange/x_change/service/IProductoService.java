package xchange.x_change.service;

import java.util.List;

import xchange.x_change.models.Producto;

public interface IProductoService {
    List<Producto> findAllProductos();
    Producto createProducto(Producto producto);
    Producto updateProductoById(Long id, Producto producto);  // Añadir esta línea
    void deleteProductoById(Long id);
    List<Producto> searchProductos(String query);
    List<Producto> filterProductosByCategoria(String categoria); 
}