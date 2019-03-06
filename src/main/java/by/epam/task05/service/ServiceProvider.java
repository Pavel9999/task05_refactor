package by.epam.task05.service;


import by.epam.task05.service.impl.CarServiceImpl;
import by.epam.task05.service.impl.ClientServiceImpl;
import by.epam.task05.service.impl.ContractServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {}
	
	private ClientService clientService = new ClientServiceImpl();
	private CarService carService = new CarServiceImpl();
	private ContractService contractService = new ContractServiceImpl();
		

	
	public ClientService getClientService() {
		return clientService;
	}
	public CarService getCarService() {
		return carService;
	}
	public ContractService getContractService() {
		return contractService;
	}

	
	public static ServiceProvider getInstance() {
		return instance;
	}

}
