package com.example.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.example.model.Produit;
import com.example.model.ProduitDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/produits")

public class ProduitServlet extends HttpServlet {
	
	private ProduitDAO dao;
	@Override
	public void init() throws ServletException {
		
		dao = (ProduitDAO) getServletContext().getAttribute("produitDAO");
		if(dao == null)
		{
			throw new ServletException("ProduitDAO non intialise");
			
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
	

		
	String action = req.getParameter("action");
	if(action == null || action.isEmpty())
	{
		
		req.setAttribute("produits", dao.findAll());
		String flash = (String) req.getSession()
	            .getAttribute("flash");

	    if (flash != null) {
	        req.setAttribute("flash", flash);
	        req.getSession().removeAttribute("flash");
	    }
	    req.getRequestDispatcher("/WEB-INF/views/produits/list.jsp")
	    .forward(req, resp);
		
	}
	else if(action.equals("new")) {
		req.setAttribute("produit",new Produit());
		req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
		   .forward(req, resp);
		
		
	}
		
	else if(action.equals("edit"))
	{
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			Produit p = dao.findById(id);
			if(p == null)
			{
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return ;
			}
			req.setAttribute("produit", p);
			req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
			   .forward(req, resp);
			
			
			
		}
		catch(NumberFormatException e) {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
		
	}
	else {
		resp.sendError(HttpServletResponse.SC_NOT_FOUND);
	}
	
	
	
	
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	throws ServletException, IOException {
	req.setCharacterEncoding("UTF-8");
	
	String action = req.getParameter("action");
	if("delete".equals(action))
	{
		try {
			Long id = Long.parseLong(req.getParameter("id"));
			Produit p = dao.findById(id);
			if(p == null)
			{
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}
		
		dao.delete(id);
		req.getSession().setAttribute("flash","Produit \"" + p.getNom() + "\" supprimé.");
		resp.sendRedirect(req.getContextPath()+"/produits");
		return;
			
			
		}
		catch(NumberFormatException e){
			
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
	}
	
	String idStr = req.getParameter("id");
	String nomStr = req.getParameter("nom");
	String descriptionStr = req.getParameter("description");
	String prixStr = req.getParameter("prix");
	String stockStr = req.getParameter("stock");
	
	if(nomStr == null)nomStr="";
	if(descriptionStr == null)descriptionStr = "";
	if(prixStr==null) prixStr ="";
	if(stockStr==null) stockStr ="";
	
	Long id = null;
	
	if(idStr != null && !idStr.trim().isEmpty())
	{
		try {
			id = Long.parseLong(idStr);
			if(dao.findById(id)==null)
			{
				resp.sendError(HttpServletResponse.SC_NOT_FOUND);
				return;
			}	
			
		}
		catch(NumberFormatException e)
		{
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
			return;
		}
		
		
	}
	
	Map <String,String> erreurs = new HashMap<>();
	
	String nom = nomStr.trim();
	prixStr = prixStr.trim();
	stockStr = stockStr.trim();
	
	if(nom.length()< 2 ||nom.length()>50 )
	{
		erreurs.put("nom","le nom doit contenir entre 2 et 50 caractére");
		
	}
	if(descriptionStr.length()>255)
	{
		erreurs.put("description", "la description ne doit pas depasser 255 caractere");
	}
	BigDecimal prix = null;
	try {
		
		if(prixStr.isEmpty())
		{
			erreurs.put("prix", "le prix est obligatoire ");
		}
		else {
			prix = new BigDecimal(prixStr);
			if(prix.compareTo(BigDecimal.ZERO)<=0)
			{
				erreurs.put("prix","le prix doit etre positive");
			}
			
		}	
	}
	
	catch(NumberFormatException e)
	{
		erreurs.put("prix", "le prix doit etre un nombre decimal");
		
	}
	
	int stock = 0;
	
	if(!stockStr.isEmpty())
	{
		try {
			stock = Integer.parseInt(stockStr);
			if(stock<0)
			{
				erreurs.put("stock", "le stock doit etre supérieur ou egale à 0");
			}
			
		}
		catch(NumberFormatException e)
		{
			erreurs.put("stock", "Le stock doit etre un nombre entier");
		}
	}
	
	Produit produit = new Produit(id,nom,descriptionStr.trim(),prix,stock);
	if(!erreurs.isEmpty())
	{
		req.setAttribute("erreurs",erreurs);
		req.setAttribute("produit", produit);
		req.getRequestDispatcher("/WEB-INF/views/produits/form.jsp")
	       .forward(req, resp);
		return;
	}
	dao.save(produit);
	String message;
	if(id==null)
	{
		message = "produit : "+nomStr+" est ajouté";
	}
	else {
		message = "produit : "+nomStr+" est modifié";
	}
	req.getSession().setAttribute("flash",message);
	
	resp.sendRedirect(req.getContextPath()+"/produits");
	}
	


}
