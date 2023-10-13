package com.example.solution_jee.servlets;

import com.example.solution_jee.dao.script.client.ClientDAO;
import com.example.solution_jee.dao.script.client.IClientDAO;
import com.example.solution_jee.model.Person;
import com.example.solution_jee.service.ClientService;
import com.example.solution_jee.service.IPersonService;
import jakarta.inject.Inject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "clients", value = "/clients")
public class ClientServlet extends HttpServlet {
    public ClientService clientService;
    @Inject
    public ClientServlet(ClientService clientService) {
        this.clientService = clientService;
    }
    @Override
    public void init() throws ServletException {
        super.init();
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(clientService);
        String uri = request.getRequestURI();
        response.setContentType(uri);

        switch (uri) {
            case "/clients" -> {
                List<Person> people = clientService.findAll();
                if (people.isEmpty()) { request.setAttribute("No_clients_found", "No clients found"); }
                else { request.setAttribute("clients", people); }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/pages/client.jsp");
                requestDispatcher.forward(request, response);
            }
            case "/clients/find" -> {

            }
        }
    }
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }
    @Override
    public void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException {

    }
    @Override
    public void destroy() {
        super.destroy();
    }
}
