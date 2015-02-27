# BankApp

Packages:
    DAccess:
      DBConnection :DBConnection()  // CONSTRUCTOR
                    Connection getConn()  // RETURNS THE CONNECTION
                    void UpdateOrInsert(String sql) throws SQLException
                    ResultSet SelectStatement(String sql) throws SQLException
                    ResultSet RetrieveFromTableName(String tablename) throws SQLException
                    void DeleteTableData(String tablename) throws SQLException
                    
      DBbank.java : void AddNewBank(String BankName) throws SQLException
                    int getBankID(String bank_name) throws SQLException // RETURN -1 IF NOT FOUND
      
      DBclient    : void AddNewClient(String firstname, String lastname, String UserName, String Password) throws SQLException
                    int getClientID(String username) throws SQLException // RETURN -1 IF NOT FOUND
                    ResultSet ClientBanks(String client_Username) throws SQLException
                    Boolean IsAvailable(String username, String password) throws SQLException
