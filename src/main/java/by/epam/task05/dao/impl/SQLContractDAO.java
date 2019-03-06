package by.epam.task05.dao.impl;

import by.epam.task05.dao.ContractDAO;
import by.epam.task05.dao.DaoException;
import by.epam.task05.entity.Contract;
import by.epam.task05.entity.ContractState;
import by.epam.task05.logger.MyLogger;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class SQLContractDAO extends SqlDao implements ContractDAO {

    private static final String ID_CHECK_CREDENTIONALS = "SELECT * FROM contract WHERE `id_contract`=?";

    private static final String INSERT_CONTRACTS_CREDENTIONALS = "INSERT INTO contract (`id_contract`, `all_price`, `id_manager`, `id_client`, `id_car`, `contract_state`, `date_start`, `date_finish`) Values (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_CONTRACT_STATE_CREDENTIONALS = "UPDATE contract SET `contract_state` = ? WHERE `id_contract` = ?";
    private static final String UPDATE_CONTRACT_MANAGER_CREDENTIONALS = "UPDATE contract SET `id_manager` = ? WHERE `id_contract` = ?";


    public int addContract(Contract contract) throws DaoException {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        int result = 0;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(INSERT_CONTRACTS_CREDENTIONALS);

            result = getNextContractID();
            st.setInt(1, getNextContractID());
            st.setFloat(2, contract.getAll_price());
            st.setInt(3, contract.getId_manager());
            st.setInt(4, contract.getId_client());
            st.setInt(5, contract.getId_car());
            st.setString(6, contract.getState().toString());
            st.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            st.setDate(8, new java.sql.Date(System.currentTimeMillis()));

            try
            {
                st.executeUpdate();
            }
            catch (SQLException e) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
            }

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);

            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return result;
    }

    public boolean cancelContract(int id) throws DaoException
    {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(UPDATE_CONTRACT_STATE_CREDENTIONALS);

            st.setString(1, ContractState.CANCELED.toString());
            st.setInt(2, id);


            st.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);

            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return true;
    }

    public boolean confirmContract(int id) throws DaoException
    {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(UPDATE_CONTRACT_STATE_CREDENTIONALS);

            st.setString(1, ContractState.CONFIRMED.toString());
            st.setInt(2, id);


            st.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);

            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return true;
    }

    public boolean finishContract(int id) throws DaoException
    {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(UPDATE_CONTRACT_STATE_CREDENTIONALS);

            st.setString(1, ContractState.FINISHED.toString());
            st.setInt(2, id);

            st.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);

            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return true;
    }

    public boolean setManager(int idContract, int idManager) throws DaoException
    {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(UPDATE_CONTRACT_MANAGER_CREDENTIONALS);

            st.setInt(1, idManager);
            st.setInt(2, idContract);


            st.executeUpdate();

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return true;
    }

    @Override
    public Contract getContract(int id) throws DaoException {
        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        Contract contract = null;

        try {
            con = ConnectionPool.getInstance().getConnection();
            st = con.prepareStatement(ID_CHECK_CREDENTIONALS);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()){
                contract = createContract(rs);
            }

            ConnectionPool.getInstance().releaseConnection(con);

        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                System.out.println(e);
                MyLogger.getInstance().error(e);
                throw new DaoException(e);
            }
            throw new DaoException(e);
        } finally {
            close(rs, st);
        }

        return contract;
    }

    @Override
    public List<Contract> selectClientContracts(int id)
    {
        List<Contract> contracts = new LinkedList<>();

        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        String sqlRequest = "SELECT * FROM contract WHERE `id_client`=? AND (`contract_state`=? OR `contract_state`=?)";

        try{
            con = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = con.prepareStatement(sqlRequest);
            statement.setInt(1, id);
            statement.setString(2, ContractState.UNCONFIRMED.toString());
            statement.setString(3, ContractState.CONFIRMED.toString());

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Contract newContract = createContract(resultSet);
                contracts.add(newContract);
            }

            ConnectionPool.getInstance().releaseConnection(con);

        }
        catch(Exception e){
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
        finally {
            close(rs, st);
        }

        return contracts;
    }

    @Override
    public List<Contract> selectManagerContracts(int id)
    {
        List<Contract> contracts = new LinkedList<>();

        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        String sqlRequest = "SELECT * FROM contract WHERE `id_manager`=? AND `contract_state`=?";

        try{
            con = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = con.prepareStatement(sqlRequest);
            statement.setInt(1, id);
            statement.setString(2, ContractState.CONFIRMED.toString());

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Contract newContract = createContract(resultSet);
                contracts.add(newContract);
            }

            ConnectionPool.getInstance().releaseConnection(con);
        }
        catch(Exception e){
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
        finally {
            close(rs, st);
        }

        return contracts;
    }

    public List<Contract> selectContractsWithState(ContractState state)
    {
        List<Contract> contracts = new LinkedList<>();

        Connection con = null;
        PreparedStatement st= null;
        ResultSet rs = null;

        String sqlRequest = "SELECT * FROM contract WHERE `contract_state`=?";

        try{
            con = ConnectionPool.getInstance().getConnection();
            PreparedStatement statement = con.prepareStatement(sqlRequest);
            statement.setString(1, state.toString());

            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Contract newContract = createContract(resultSet);
                contracts.add(newContract);
            }

            ConnectionPool.getInstance().releaseConnection(con);

        }
        catch(Exception e){
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
        finally {
            close(rs, st);
        }

        return contracts;
    }

    private Contract createContract(ResultSet resultSet) throws SQLException {
        Contract contract = new Contract();

        int _id = resultSet.getInt(1);
        float price = resultSet.getFloat(2);
        int managerID = resultSet.getInt(3);
        int clientID = resultSet.getInt(4);
        int id_car =  resultSet.getInt(5);
        String state = resultSet.getString(6);

        contract.setId(_id);
        contract.setAll_price(price);
        contract.setId_manager(managerID);
        contract.setId_client(clientID);
        contract.setId_car(id_car);
        contract.setState(ContractState.fromString(state));

        return contract;
    }

    private int getNextContractID()
    {
        int id = 0;

        try{
            try (Connection conn = DriverManager.getConnection(url, login, password)){


                Statement statement = conn.createStatement();
                String sqlRequest = "SELECT COUNT(*) FROM contract";
                ResultSet resultSet = statement.executeQuery(sqlRequest);
                if (resultSet.next()){
                    id = resultSet.getInt(1) + 1;
                }
            }
        }
        catch(Exception e){
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }

        return id;
    }


    private void close(ResultSet rs, PreparedStatement st) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
        try {
            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.out.println(e);
            MyLogger.getInstance().error(e);
        }
    }
}
