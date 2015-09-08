package br.net.mirante.singular.service;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.net.mirante.singular.dao.CategoryMenuDAO;
import br.net.mirante.singular.dao.MenuItemDTO;

@Service("menuService")
public class MenuServiceImpl implements MenuService {

    @Inject
    private CategoryMenuDAO categoryMenuDAO;

    @Override
    @Transactional
    @Cacheable(value = "retrieveAllCategoriesMenu", cacheManager = "cacheManager")
    public List<MenuItemDTO> retrieveAllCategories() {
        return categoryMenuDAO.retrieveAll();
    }
}
