package server;

import persistences.*;

import java.sql.*;


public class CardsRequest {
    CardsData cardsData = new CardsData();
    public String deleteCards(String[] path) throws SQLException {
        int customerId = Integer.parseInt(path[2]);
        int idCard = Integer.parseInt(path[4]);

        return cardsData.deleteCard(customerId, idCard);
    }
}
