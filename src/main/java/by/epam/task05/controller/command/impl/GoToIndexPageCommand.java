package by.epam.task05.controller.command.impl;



import by.epam.task05.controller.command.Command;
import by.epam.task05.controller.command.util.CreatorFullURL;
import by.epam.task05.logger.MyLogger;
import by.epam.task05.service.ServiceProvider;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class GoToIndexPageCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {

		String url = CreatorFullURL.create(request);
		HttpSession session = request.getSession(true);

		session.setAttribute("prev_request", url);

		String newLocale = "ru";
		session.setAttribute("local", newLocale);

		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/default.jsp");

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
