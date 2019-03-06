package by.epam.task05.controller;



import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.CommandProvider;
import by.epam.task05.dao.DaoException;
import by.epam.task05.service.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String PARAMETER_COMMAND = "command";
	
	private final CommandProvider provider = new CommandProvider();
	

	
    public Controller() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String commandName = request.getParameter(PARAMETER_COMMAND);
		Command command = provider.getCommand(commandName);

		try {
			command.execute(request, response);
		} catch (ServiceException e) {
			e.printStackTrace();
		} catch (DaoException e) {
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
