package com.mspr.integrationContinue.integrationContinue.Controller;

import com.mspr.integrationContinue.integrationContinue.Entity.Produit;
import com.mspr.integrationContinue.integrationContinue.Repository.ProduitRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/Produits")
public class ProduitController {

    @Autowired
    private final ProduitRepository produitRepository;

    private final Logger logger = Logger.getLogger(ProduitController.class);

    public ProduitController(ProduitRepository produitRepository) {
        this.produitRepository = produitRepository;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Collection<Produit> getList() {
        logger.debug("Get all produits!");
        Iterable<Produit> produitIterable = produitRepository.findAll();
        Collection<Produit> produits = new ArrayList<>((Collection<? extends Produit>) produitIterable);
        logger.debug(produits);
        return produits;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public Produit afficherProduit(@PathVariable int id) {
        logger.debug("produits : " + id);
        Optional<Produit> produits = produitRepository.findById(id);
        logger.debug(produits);
        return produits.get();
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
    public Produit save(@RequestBody Produit produit) {
        logger.debug(produit);
        produit = produitRepository.save(produit);
        return produit;
    }


    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public String delete(@PathVariable int id) {
        logger.debug("produit id: "+id);
        produitRepository.deleteById(id);
        return "produit supprimé !";
    }


}
