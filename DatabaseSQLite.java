package my.uum;

import java.sql.*;

/**
 * This class is to connect JDBC and implements method for insert, delete, update and display function from table
 *
 * @author Tan Yee Jin
 */
public class DatabaseSQLite {
    private Connection connect(){
        String jdbcUrl = "jdbc:sqlite:C:\\Users\\user\\IdeaProjects\\assignment-2-TanYeeJin-279890\\identifier.sqlite";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(jdbcUrl);
        }catch(SQLException e){
            System.out.println("Error connecting to SQL Database.");
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * This method is to insert the data of the user into the table user_test.
     *
     * @param ic The I/C number of the user.
     * @param id The staff id of the user.
     * @param name The name of the user.
     * @param contact The phone number of the user.
     * @param email The email of the user.
     * @param reason The purpose of the meeting room booked by user.
     */
    public void insertData(String ic,String id,String name,String contact,String email,String reason){
        String sql = "INSERT INTO user_test(icno,staffID,user_name,phone,email,purpose) VALUES(?,?,?,?,?,?)";
        try(Connection conn = this.connect();
                PreparedStatement data = conn.prepareStatement(sql)) {
                data.setString(1, ic);
                data.setString(2, id);
                data.setString(3, name);
                data.setString(4, contact);
                data.setString(5, email);
                data.setString(6, reason);
                data.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }

    /**
     * This method is to update the status of the meeting room according to the room id that being chosen by the user.
     *
     * @param id The staff id of the user.
     * @param rmid The room id selected by the user.
     * @param date The date available for the meeting room that being chosen by the user.
     * @param timeSlot The time slot that being chosen by the user.
     */
    public void updateStatus(String id,String rmid,String date,String timeSlot){
        String sql = "UPDATE table_room SET status = 'unavailable', user_id = ? WHERE rmid = ? AND date = ? AND timeSlot = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.setString(2, rmid);
            pstmt.setString(3, date);
            pstmt.setString(4, timeSlot);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(rmid+" booked at "+date+"\t"+timeSlot+" by "+id);
    }

    /**
     * This method is to insert the data of room id, staff id, booking date, and booking time into table_book.
     *
     * @param rmid The room id of the meeting room that being chosen by the user.
     * @param staffid The staff id of the user.
     * @param date The available date that being chosen by user to book.
     * @param time The time of time slot that being chosen by user to book.
     */
    public void insertBook(String rmid, String staffid, String date, String time){
        String sql = "INSERT INTO table_book(room_id, user_id, bookDate, bookTime) VALUES (?,?,?,?)";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, rmid);
            pstmt.setString(2, staffid);
            pstmt.setString(3, date);
            pstmt.setString(4, time);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(rmid+","+staffid);
    }

    /**
     * This method is to delete row in table_book if user cancel booking.
     *
     * @param staffid The staff id of the user.
     * @return Return the message of cancel booking successful when the row of data is being deleted.
     */
    public String cancelBook(String staffid){
        String sql = "DELETE FROM table_book\n" +
                "WHERE user_id IN (\n" +
                "SELECT user_id FROM table_book a\n" +
                "INNER JOIN user_test b\n" +
                "ON (a.user_id=b.staffID)\n" +
                "WHERE b.staffID = ?\n" +
                ")";

        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, staffid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Cancel booking room for "+staffid);
        String message = "Booking successfully cancelled. Have a nice day!";
        return message;
    }

    /**
     * This method is to delete row of user in table user_test if user cancel booking.
     *
     * @param staffid The staff id of the user.
     */
    //delete from user table
    public void deleteUser(String staffid){
        String sql = "DELETE FROM user_test WHERE staffID = ?";

        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, staffid);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Delete User for "+staffid);
    }

    /**
     * This method is to update status of the meeting room to available when user decided to cancel booking.
     *
     * @param id The staff id of the user.
     */
    public void updateCancel(String id){
        String sql = "UPDATE table_room SET status = 'available', user_id = NULL WHERE user_id = ?";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Update Cancel Book for "+id);
    }

    //DISPLAY
    /**
     * This method is to return the available date for meeting room.
     *
     * @return The room id , available date and the maximum capacity of the room.
     */
    public String getDate(){
        String rs;
        String rs1 = "";
        String sql = "SELECT*FROM table_room WHERE status = 'available' ORDER BY date DESC";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet result  = pstmt.executeQuery();
            //rs1="Room ID\t\t\tMax Capacity\t\t\tDate Available";
            // loop through the result set
            while (result.next()) {
                rs = result.getString("rmid") +"\t\t\t\t\t\t\t\t"+ result.getInt("pax")
                        +"\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t"+ result.getString("date")+"\n";
                StringBuffer bfr = new StringBuffer(rs);
                bfr.append(rs1);
                rs1=rs+rs1;
            }
            System.out.println(rs1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs1;
    }

    /**
     * This method is to return the meeting room id and the maximum capability of the meeting room.
     *
     * @param Date The date user enter to book meeting room.
     * @return The room id and the maximum capacity of the room entered by the user.
     */
    public String getRoom(String Date){
        String rs;
        String rs1 = "";
        String sql = "SELECT*FROM table_room WHERE date = ? AND status = 'available'";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Date);
            ResultSet result  = pstmt.executeQuery();

            // loop through the result set
            while (result.next()) {
                rs = result.getString("rmid") +"\t\t\t\t\t"+ result.getInt("pax") + "\n";
                StringBuffer bfr = new StringBuffer(rs);
                bfr.append(rs1);
                rs1=rs+rs1;
            }
            System.out.println(rs1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs1;
    }

    /**
     * This method is to return the meeting room id, meeting room status at that date,description of the meeting room selected by the user
     *
     * @param roomID The id of the meeting room.
     * @param date The user booking date.
     * @return The room id, available date and description of the room entered by the user.
     */
    public String getRoomDesc(String roomID,String date){
        String rs = "";
        String sql = "SELECT table_room.rmid, table_room.date, table_room.status, table_desc.roomDesc FROM table_room INNER JOIN table_desc ON table_room.rmid = table_desc.roomid  WHERE roomid = ? AND date = ? AND status = 'available'";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomID);
            pstmt.setString(2, date);
            ResultSet result  = pstmt.executeQuery();

            // loop through the result set
            while (result.next()) {
                rs = "RoomID: "+roomID+"\tavailable at: "+date+"\nRoom Description: \n"+result.getString("roomDesc") +"\n";
            }
            System.out.println(rs);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }

    /**
     * This method is to return time slot of the meeting room from table_room.
     *
     * @param roomid The id of the meeting room.
     * @param date The booking date by user input.
     * @return The list of time slot that available for the meeting room.
     */
    public String getTimeSlot(String roomid,String date){
        String rs;
        String rs1 = "";
        String sql = "SELECT*FROM table_room WHERE rmid = ? AND date = ? AND status = 'available' ORDER BY timeSlot DESC";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, roomid);
            pstmt.setString(2, date);
            ResultSet result  = pstmt.executeQuery();

            // loop through the result set
            while (result.next()) {
                rs = "\n"+result.getString("timeSlot") +"\n";
                StringBuffer bfr = new StringBuffer(rs);
                bfr.append(rs1);
                rs1=rs+rs1;
            }
            System.out.println(rs1);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs1;
    }
    /**
     * This method is to check user's existance in the table_book.
     *
     * @param id The staff id of user.
     * @return The searching result.(if user exisit display booking details, if not display not found message)
     */
    public String checkUser(String id) {
        String rs1 = "";
        try {
            String query = "SELECT (count(*) > 0) as found FROM table_book WHERE user_id = ?";
            Connection conn = this.connect();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, id);

            try (ResultSet rs = pst.executeQuery()) {
                // Only expecting a single result
                if (rs.next()) {
                    boolean found = rs.getBoolean(1); // "found" column
                    if (found) {
                        // You have rows
                        rs1 = searchList(id);
                        System.out.println(rs1);
                    } else {
                        rs1 = "Opps! you are not found in list.";
                        System.out.println(rs1);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs1;
    }

    /**
     * This method is to return user data by searching user in table user_test where select from table_book inner join table_room, table user_test, and table_desc
     *
     * @param id The staff id of user.
     * @return The history of user booking meeting room along with user's data.
     */
    public String searchList(String id){
        String rs;
        String rs1 = "";
        String sql = "SELECT c.staffid, c.user_name, c.phone, c.purpose,c.email,r.rmid,r.pax,r.date,d.roomDesc,r.timeSlot " +
                    "FROM table_book b " +
                    "INNER JOIN table_room r ON r.rmid = b.room_id\n" +
                    "INNER JOIN user_test c ON c.staffid = b.user_id\n" +
                    "INNER JOIN table_desc d on d.roomid = b.room_id " +
                    "WHERE b.user_id = ? AND r.status='unavailable'";
            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, id);
                ResultSet result  = pstmt.executeQuery();
                // loop through the result set
                while (result.next()) {
                    rs = "Name:\t\t"+result.getString("user_name")
                            +"\nEmail:\t\t"+result.getString("email")
                            +"\nStaff ID:\t"+result.getString("staffid")
                            +"\nContact:\t"+result.getString("phone")
                            +"\nReason:\t\t"+result.getString("purpose")
                            +"\n\nRoom Booked\t\t\tMax Capacity\n"+
                            result.getString("rmid")+"\t\t\t\t\t\t\t\t\t\t"+result.getString("pax")
                            +"\n\nRoom Description:\n"+result.getString("roomDesc")
                            +"\nBooking Date:\t"+result.getString("date")
                            +"\nBooking Time:\t"+result.getString("timeSlot");
                    StringBuffer bfr = new StringBuffer(rs);
                    bfr.append(rs1);
                    rs1=rs+rs1;
                }
                System.out.println(rs1);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        return rs1;
    }

    /**
     * This method is to return a list of users that has booked for meeting room.
     * The data that will be display out include book_id, user_name, email, staffid, phone, purpose, roomid, capacity, room description, date and time slot.
     * @return The users list with the required data.
     */
    public String displayList(){
        String rs;
        String rs1 = "";
        String sql = "SELECT table_book.book_id,user_test.staffid, table_room.rmid,user_test.user_name, user_test.phone, user_test.purpose,\n" +
                "       user_test.email,table_room.pax,table_room.date,table_desc.roomDesc,table_room.status,\n" +
                "       table_room.timeSlot\n" +
                "FROM table_book INNER JOIN table_room ON table_room.rmid = table_book.room_id\n" +
                "                INNER JOIN user_test ON user_test.staffid = table_room.user_id\n" +
                "                INNER JOIN table_desc on table_desc.roomid = table_room.rmid WHERE table_room.status = 'unavailable' ORDER BY table_book.rowid DESC";
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet result  = pstmt.executeQuery();
            // loop through the result set
            while (result.next()) {
                rs = "\n\nBookID:\t\t"+result.getString("book_id")
                        +"\n\nName:\t\t"+result.getString("user_name")
                        +"\nEmail:\t\t"+result.getString("email")
                        +"\nStaff ID:\t"+result.getString("staffid")
                        +"\nContact:\t"+result.getString("phone")
                        +"\nReason:\t\t"+result.getString("purpose")
                        +"\n\nRoom Booked\t\tMax Capacity\n"+
                        result.getString("rmid")+"\t\t\t\t"+result.getString("pax")
                        +"\n\nRoom Description:\n"+result.getString("roomDesc")
                        +"\nBooking Date:\t"+result.getString("date")
                        +"\nBooking Time:\t"+result.getString("timeSlot");
                StringBuffer bfr = new StringBuffer(rs);
                bfr.append(rs1);
                rs1=rs+rs1;
            }
            System.out.println(rs1);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return rs1;
    }
}
