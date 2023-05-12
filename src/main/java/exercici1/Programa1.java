package exercici1;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Projections;
import org.bson.Document;

import java.util.Arrays;
import java.util.Date;

public class Programa1 {
    public static void main(String[] args) {
        //Establecer conexión con servidor MongoDB
        MongoClient mongoClient = MongoClients.create("mongodb://localhost:27017");
        MongoDatabase database = mongoClient.getDatabase("restaurante");

        // obtener colección "restaurante"
        MongoCollection<Document> collection = database.getCollection("restaurante");


        /*1*/FindIterable<Document> documentos1 = collection.find();
        /*2*/FindIterable<Document> documentos2 = collection.find().projection(new Document("restaurant_id", 1).append("name", 1).append("borough", 1).append("cuisine", 1));
        /*3*/FindIterable<Document> documentos3 = collection.find().projection(new Document("_id", 0).append("restaurant_id", 1).append("name", 1).append("borough", 1).append("cuisine", 1));
        /*4*/FindIterable<Document> documentos4 = collection.find().projection(new Document("_id", 0).append("restaurant_id", 1).append("name", 1).append("borough", 1).append("address.zipcode", 1));
        /*5*/FindIterable<Document> documentos5 = collection.find(new Document("borough", "Bronx"));
        /*6*/FindIterable<Document> documentos6 = collection.find(new Document("borough", "Bronx")).limit(5);
        /*7*/FindIterable<Document> documentos7 = collection.find(new Document("borough", "Bronx")).skip(5).limit(5);
        /*8*/FindIterable<Document> documentos8 = collection.find(new Document("score", new Document("$gt", 90)));
        /*9*/FindIterable<Document> documentos9 = collection.find(new Document("score", new Document("$gt", 80).append("$lt", 100)));
        /*10*/FindIterable<Document> documentos10 = collection.find(new Document("address.coord.0", new Document("$lt", -95.754168)));
        /*11*/FindIterable<Document> documentos11 = collection.find(new Document("cuisine", new Document("$ne", "American")).append("grades.score", new Document("$gt", 70)).append("address.coord.0", new Document("$lt", -65.754168)));
        /*12*/FindIterable<Document> documentos12 = collection.find(new Document("cuisine", new Document("$ne", "American")).append("grades.score", new Document("$gt", 70)).append("address.coord.0", new Document("$lt", -65.754168)));
        /*13*/FindIterable<Document> documentos13 = collection.find(new Document("cuisine", new Document("$ne", "American")).append("grades.grade", "A").append("borough", new Document("$ne", "Brooklyn"))).sort(new Document("cuisine", -1));
        /*14*/FindIterable<Document> documentos14 = collection.find(new Document("name", new Document("$regex", "^Wil"))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*15*/FindIterable<Document> documentos15 = collection.find(new Document("name", new Document("$regex", "ces$"))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*16*/FindIterable<Document> documentos16 = collection.find(new Document("name", new Document("$regex", ".*Reg.*"))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*17*/FindIterable<Document> documentos17 = collection.find(new Document("borough", "Bronx").append("cuisine", new Document("$in", Arrays.asList("American", "Chinese"))));
        /*18*/FindIterable<Document> documentos18 = collection.find(new Document("borough", new Document("$in", Arrays.asList("Staten Island", "Queens", "Bronx", "Brooklyn")))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*19*/FindIterable<Document> documentos19 = collection.find(new Document("borough", new Document("$nin", Arrays.asList("Staten Island", "Queens", "Bronx", "Brooklyn")))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*20*/FindIterable<Document> documentos20 = collection.find(new Document("grades.score", new Document("$not", new Document("$gt", 10)))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*21*/FindIterable<Document> documentos21 = collection.find(new Document("$or", Arrays.asList(new Document("cuisine", "Fish").append("name", new Document("$regex", "^Wil")), new Document("cuisine", new Document("$nin", Arrays.asList("American", "Chinese")))))).projection(Projections.include("restaurant_id", "name", "borough", "cuisine"));
        /*22*/FindIterable<Document> documentos22 = collection.find(new Document("grades", new Document("$elemMatch", new Document("grade", "A").append("score", 11).append("date", new Document("$eq", new Date("2014-08-11T00:00:00Z")))))).projection(Projections.include("restaurant_id", "name", "grades"));
        /*23*/FindIterable<Document> documentos23 = collection.find(new Document("grades.1", new Document("grade", "A").append("score", 9).append("date", new Document("$eq", new Date("2014-08-11T00:00:00Z"))))).projection(Projections.include("restaurant_id", "name", "grades"));
        /*24*/FindIterable<Document> documentos24 = collection.find(new Document("coord.1", new Document("$gt", 42).append("$lte", 52))).projection(Projections.include("restaurant_id", "name", "address", "coord"));
        /*25*/FindIterable<Document> documentos25 = collection.find().sort(new Document("name", 1));
        /*26*/FindIterable<Document> documentos26 = collection.find().sort(new Document("name", -1));
        /*27*/FindIterable<Document> documentos27 = collection.find().sort(new Document("cuisine", 1).append("borough", -1));
        /*28*/FindIterable<Document> documentos28 = collection.find(new Document("address", new Document("$regex", ".*street.*").append("$options", "i")));
        /*29*/FindIterable<Document> documentos29 = collection.find(new Document("coord", new Document("$type", "double")));
        /*30*/FindIterable<Document> documentos30 = collection.find(new Document("$expr", new Document("$eq", Arrays.asList("$mod", Arrays.asList("$score", 7), 0)))).projection(Projections.include("restaurant_id", "name", "grade"));
        /*31*/FindIterable<Document> documentos31 = collection.find(new Document("name", new Document("$regex", ".*mon.*").append("$options", "i"))).projection(Projections.include("name", "borough", "coord.longitude", "coord.latitude", "cuisine"));
        /*32*/FindIterable<Document> documentos32 = collection.find( new Document("name", new Document("$regex", "^Mad").append("$options", "i"))).projection(Projections.include("name", "borough", "coord.longitude", "coord.latitude", "cuisine"));







        // Cerrar conexión con MongoDB
        mongoClient.close();
    }
}