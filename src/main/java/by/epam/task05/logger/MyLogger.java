package by.epam.task05.logger;

import org.apache.log4j.Logger;

public class MyLogger
{
    private static final MyLogger instance = new MyLogger();
    public static MyLogger getInstance() {
        return instance;
    }

    public final Logger logger;

    public MyLogger(){
        logger = Logger.getLogger(MyLogger.class);
    }

    public void error(Exception e)
    {
        logger.error("exception", e);
    }

    public void error(String e)
    {
        logger.error(e);
    }
}
