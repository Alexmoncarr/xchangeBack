package xchange.x_change.service.implementation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xchange.x_change.models.Producto;
import xchange.x_change.repository.ProductoRepository;
import xchange.x_change.service.IProductoService;

@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto updateProductoById(Long id, Producto producto) {
        Producto existingProducto = productoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Producto con id " + id + " no encontrado"));
        existingProducto.setNombre(producto.getNombre());
        existingProducto.setDescripcion(producto.getDescripcion());
        existingProducto.setPrecio(producto.getPrecio());
        existingProducto.setUrlImagen(producto.getUrlImagen());
        return productoRepository.save(existingProducto);
    }

    @Override
    @Transactional
    public void deleteProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
            .orElseThrow(() -> new IllegalStateException("Producto con id " + id + " no encontrado"));
        productoRepository.delete(producto);
    }

    @Override
    public List<Producto> searchProductos(String query) {
        return productoRepository.findByNombreContainingOrDescripcionContaining(query, query);
    }

    @Override
    public List<Producto> filterProductosByCategoria(String categoria) {
        return productoRepository.findByCategoria(categoria);
    }
}