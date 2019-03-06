package by.epam.task05.controller.command.impl;

import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.Contract;
import by.epam.task05.entity.ContractData;
import by.epam.task05.entity.User;
import by.epam.task05.entity.UserRole;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.ClientService;
import by.epam.task05.service.ContractService;
import by.epam.task05.service.ServiceException;
import by.epam.task05.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class StartRentCommand implements Command {

    private static final String CLIENT_CONTRACTS_PAGE = "/WEB-INF/views/client_contracts.jsp";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException, DaoException {
        HttpSession session;
        String email;
        UserRole role;
        String url;
        String page;

        session = request.getSession(true);
        email = (String)session.getAttribute("email");
        role = UserRole.createRole((String)session.getAttribute("role")) ;
        url = CreatorFullURL.create(request);
        session.setAttribute("prev_request", url);

        ServiceProvider provider = ServiceProvider.getInstance();
        ClientService clientService = provider.getClientService();
        ContractService contractService = provider.getContractService();


        User user = clientService.getUser(email);
        int idUser = user.getId();
        int idCar = Integer.valueOf(request.getParameter("car_id"));

        try{
            contractService.startRent(idUser, idCar);
        }catch (Exception e)
        {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        page = CLIENT_CONTRACTS_PAGE;
        List<ContractData> contracts = new LinkedList<>();
        try{
            contracts =  contractService.selectClientContracts(idUser);
        }catch (Exception e)
        {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        request.setAttribute("contracts", contracts);

        RequestDispatcher dispatcher = request.getRequestDispatcher(page);


        try {
            dispatcher.forward(request, response);
        }
        catch (Exception e)
        {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
    }
}
