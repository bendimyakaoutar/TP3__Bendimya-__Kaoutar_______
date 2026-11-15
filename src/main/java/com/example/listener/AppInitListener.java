package com.example.listener;

import com.example.model.ProduitDAO;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener

public class AppInitListener implements ServletContextListener {

	
		
		@Override
		public void contextInitialized(ServletContextEvent sce) {
			
			ServletContext  context = sce.getServletContext();
			ProduitDAO dao= new ProduitDAO();
			context.setAttribute("produitDAO", dao);
			System.out.println("ProduitDAO intialisé ");
			
		}
		@Override
		public void contextDestroyed(ServletContextEvent sce)
		{
			System.out.println("Apllication arreté");
		}
		


}
