package com.algorithms.aprenderypractique.Lyft;

import com.algorithms.aprenderypractique.BaseTest;

import java.util.*;

public class Solve extends BaseTest {

    @org.junit.Test
    public void solution() {
        Controller controller = new Controller();
        controller.executeCommand("SET a 50");
        System.out.println(controller.executeCommand("GET a"));
        controller.executeCommand("BEGIN");
        System.out.println(controller.executeCommand("GET a"));
        controller.executeCommand("SET a 60");
        controller.executeCommand("BEGIN");
        System.out.println(controller.executeCommand("GET a"));
        controller.executeCommand("ROLLBACK");
        System.out.println(controller.executeCommand("GET a"));
        controller.executeCommand("UNSET a");
        System.out.println(controller.executeCommand("GET a"));
        controller.executeCommand("COMMIT");
        System.out.println(controller.executeCommand("NUMWITHVALUE 60"));
        controller.executeCommand("END");
    }

    class Controller {
        List<Transaction> transactions;
        int pointer;

        Controller() {
            transactions = new ArrayList<>();
            beginTransaction();
            pointer = 0;
        }

        Object executeCommand(String command) {
            String[] operation = command.split(" ");
            switch(operation[0]) {
                case "BEGIN":
                    beginTransaction();
                    break;
                case "SET":
                    transactions.get(pointer).db.set(operation[1], Integer.parseInt(operation[2]));
                    break;
                case "UNSET":
                    transactions.get(pointer).db.unSet(operation[1]);
                    break;
                case "GET":
                    return transactions.get(pointer).db.get(operation[1]);
                case "NUMWITHVALUE":
                    return transactions.get(pointer).db.numWithValue(Integer.parseInt(operation[1]));
                case "COMMIT":
                    commitTransaction();
                    break;
                case "ROLLBACK":
                    rollback();
                    break;
                case "END":
                    transactions = null;
                    break;
                default:
                    return "No Command Found";
            }
            return "Command Executed Successfully";
        }

        void beginTransaction() {
            if(!transactions.isEmpty()) {
                Database parentDB = transactions.get(transactions.size()-1).db;
                transactions.add(new Transaction(parentDB));
            }
            else {
                transactions.add(new Transaction());
            }
            pointer++;
        }

        void commitTransaction() {
            Transaction currTransaction = transactions.get(pointer);
            Transaction parentTransaction = transactions.get(pointer-1);

             for(Map.Entry<String, Integer> entry : currTransaction.db.data.entrySet()) {
                 parentTransaction.db.data.put(entry.getKey(), entry.getValue());
             }

            transactions.remove(pointer);
            pointer--;
        }

        void rollback() {
            transactions.remove(pointer);
            pointer--;
        }
    }

    class Transaction {
        public Database db;

        Transaction() {
            db = new Database();
        }
        Transaction(Database parentDB) {
            db = parentDB;
        }
    }

    class Database {
        Map<String, Integer> data;

        Database() {
            data = new HashMap<>();
        }

        void set(String key, int value) {
            if(key != null && !key.isBlank())
                data.put(key.trim(), value);
        }

        Integer get(String key) {
            if(key != null && !key.isBlank() && data != null && data.containsKey(key))
                return data.get(key);

            return null;
        }

        void unSet(String key) {
            if(key != null && !key.isBlank() && data != null && data.containsKey(key))
                data.remove(key.trim());
        }

        Integer numWithValue(Integer value) {
            // return data.values().stream().filter(v -> v.equals(value)).count();
            // return Collections.frequency(data.values(), value);

            if(data == null)    return 0;

            int sum = 0;
            for(Map.Entry<String, Integer> entry : data.entrySet()) {
                if(entry.getValue().intValue() == value.intValue())
                    sum++;
            }
            return sum;
        }
    }

}
