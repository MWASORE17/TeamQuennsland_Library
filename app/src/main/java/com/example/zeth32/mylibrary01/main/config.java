package com.example.zeth32.mylibrary01.main;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.zeth32.mylibrary01.R;
import com.example.zeth32.mylibrary01.main.entity.Book;
import com.example.zeth32.mylibrary01.main.entity.User;
import com.example.zeth32.mylibrary01.main.home_fragment.customClass;
import com.example.zeth32.mylibrary01.main.session.LoginSession;
import com.example.zeth32.mylibrary01.main.startapp_view.Login;

import java.util.Calendar;

/**
 * Created by Zeth32 on 14/06/2017.
 */

public class config  extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.config);
        setBook();
        setAlarm();
        Intent intent = new Intent(config.this, StartApp.class);
        startActivityForResult(intent,0);
        this.finish();
    }
    public void setBook(){
        Book.books.clear();

        // Art
        Book newBook = new Book("9781855682979","UNTUK APA SENI", 10, "Untuk apa seni? Dalam dunia kontemporer yang dikelola oleh nalar ilmiah dan teknologis secara menyeluruh ini, apa pentingnya seni? Ketika orang sudah terpenjara oleh kepentingan kalkulatif dan nalar strategis-praktis, untuk apa seni? Ketika hari-hari ini konon seni modern yang canggih pun dinyatakan ‘sudah mati’, ketika seni tradisional juga tak lagi punya gigi, untuk apakah gerangan seni?\n" +
                " Ironisnya, pada saat yang sama kini ‘seni’ justru merupakan paradigma di segala sisi hidup manusia. Kata ‘seni’ bukan saja dikenakan pada berbagai bidang seperti: seni kuliner, seni entrepreneurship, seni berpolitik, seni merancang lingkungan, dst, tapi bahkan proses penemuan-penemuan ilmiah pun kini cenderung dipahami sebagai proses kreatif, mirip proses berkesenian. Dan dalam dunia industri, seni justru merupakan fokus utama: yang dimainkan di sana bukanlah sekadar desain produk atau komunikasi visual, melainkan gaya hidup, seni menjalani hidup. Dan ketika rasio-nalisme modern telah banyak dikritik secara mendasar, di era postmodern ini orang melihat kembali pentingnya imajinasi dan perasaan, pentingnya seni. Pendeknya, kini segala hal justru cenderung dilihat sebagai ‘seni’.\n" +
                "Dalam situasi paradoksal itu, buku ini hendak menjelaskan secara mendalam dan menyeluruh hakikat, riwayat, kontroversi, posisi dan fungsi seni dalam peradaban manusia. Seni dalam arti luas, tetapi juga dalam arti sempitnya, seperti seni tari, lukis, patung, musik, teater, desain dan sastra. Sebuah buku penting bagi siapa pun yang terlibat dalam pendidikan seni atau pun hendak mempelajari seni.", "Banyak", "Art", 8);
        Book.books.add(newBook);

        newBook = new Book("9781855682970","Seni123", 90, "Seni Seni Seni", "Siapa01?", "Art", 1);
        Book.books.add(newBook);

        newBook = new Book("9781855682971","Sementara Berseni", 8, "Seni Seni Seni", "Siapa02?", "Art", 2);
        Book.books.add(newBook);

        newBook = new Book("9781855682972", "Senin Berseni", 32, "Seni Seni Seni", "Siapa03?", "Art", 3);
        Book.books.add(newBook);


        //Travel
        newBook = new Book("9781855682973","The Best of FUKUOKA", 20, "Ketika berbicara tentang Jepang, apa yang terpikir di kepala Anda? Tokyo, ibukotanya yang supermodern itukah? Atau, Kyoto dengan kuil-kuil yang indah dan nuansa tradisional yang kental? Atau mungkin juga Osaka dan Sapporo? Bagaimana dengan Fukuoka?\n" +
                "\n" +
                "Fukuoka digelari sebagai one of the most livable city di seluruh dunia. WOW! Fukuoka adalah sebuah perpaduan yang indah. Kotanya begitu rapi, tertata, dan modern, dengan aneka objek yang bisa kita kunjungi.\n" +
                "Buku ini adalah panduan yang superlengkap untuk merasakan Jepang dalam banyak tempat, dan aktivitas, di kota Fukuoka. Jangan khawatir, panduan di buku ini sangat terperinci, dengan kondisi yang paling aktual.", "Nadira Gandi", "Travel", 3);
        Book.books.add(newBook);

        newBook = new Book("9781855682974","101 Korean Shopping Guide", 24, "Korea adalah surganya belanja. Mulai dari barang-barang branded, pakaian, kosmetik, elektronik, pernak-pernik lucu, perhiasan, pernak-pernik berbau tradisional khas Korea, makanan, dan masih banyak lagi bisa didapatkan dengan mudah di segala penjuru Korea. Pemerintah Korea nampaknya menyadari benar hal ini. Itulah sebabnya mereka memiliki banyak program belanja di Korea, misalnya Seoul Summer Sale atau Korea Grand Sales yang diselenggarakan tiap tahun, dengan promo terencana dan menggunakan idola-idola Korea yang ngetop sebagai ambassador-nya.\n" +
                "Harga-harga barang di Korea memang tidak tergolong super murah seperti di negara lain. Namun harga yang tidak terlalu murah ini masih tergolong cukup murah jika kita melihat mutu serta desain barang yang ditawarkan di Korea. Meski demikian, ada juga barang yang dikategorikan dijual dengan harga murah di Korea. Harga produk kosmetik asli Korea (ETUDE, Face Shop, dan lain-lain) di sana dijual dengan harga sekitar 30-50% lebih murah dibandingkan kosmetik bermerek sama yang dijual di Indonesia.\n" +
                "Kemudahan-kemudahan inilah, plus produk-produk yang memang memiliki kualitas tinggi, yang menjadikan Korea menjadi destinasi wisata belanja bagi wisatawan asing. Jadi… wajar dong jika kita juga kepengin ikutan wisata belanja di Korea. Bagi yang sudah gatal ingin belanja di Korea, yuk baca dulu serba-serbi soal belanja di Korea!\n", "Olivia Kristie","Travel", 9);
        Book.books.add(newBook);

        newBook = new Book("9781855682975", "The Travel Crates", 8, "Dalam buku ini, dr. Rudy menyajikan pengalam¬annya berkeliling dunia selama menjadi mahasiswa kedokteran dari sudut pandang yang berbeda-beda. Cerita¬nya mengalir bagaikan mozaik-mozaik warna-warni.\n" +
                "Mardi Wu (@wumard) - World traveller, CEO Nutrifood Indonesia\n" +
                "Tidak sekadar bertualang, namun juga mengemban beragam misi untuk nege¬ri. Buku ini menawarkan tips dan trik menarik juga edukatif! A must have!\n" +
                "Arya Ondrio (@Aondrio) - Hilo Green Ambassador 2013, Founder and President of Indonesian MUN Society\n" +
                "Bibir saya terus menerus tertarik ke atas, puas rasanya membayangkan cerita demi cerita yang ditulis dengan sangat photographic oleh dr. Rudy. \n" +
                "Chichi Bernardus (@chichibernardus) - Mother of four, Climate Activist, Food Enthusiast, Editor-in-chief Women’s Health Indonesia dan Men’s Health Indonesia\n" +
                "Dari tulisan ini, gue yakin makin banyak orang yang akan berani memu¬lai untuk bepergian, demi mendapat pengalaman baru, yang pastinya bikin para pencinta traveling semakin penasaran.\n" +
                "Shafira umm (@ShafiraUmm) - presenter entertainment news NET TV\n","Rudy Kurniawan", "Travel", 2);
        Book.books.add(newBook);

        newBook = new Book("9781855682976", "Menghirup Dunia",8,"Apa yang Anda lakukan sebagai seorang traveler? Dulu pernah tercetus di beberapa percakapan di media sosial, bahwa ada perbedaan antara turis dan traveler, bahwa turis semata datang dan menghabiskan uang demi memuaskan diri dengan segala objek yang tersaji, sementara traveler adalah orang yang lebih dari itu. Benarkah?\n" +
                "\n" + "Garis perbedaan itu tampaknya tidak pernah tersepakati, dan tidak akan. Selain terkesan terlalu menghakimi, urusan berwisata atau berjalan-jalan adalah urusan pribadi. Dan jika Anda ingin lebih dari itu, Anda bisa melakukannya tanpa ada embel-embel identitas apa pun.\n" +
                "\n" + "Buku ini merupakan kumpulan kisah orang-orang yang memiliki hobi berjalan-jalan, menjelajah area di luar teritori mereka. Kisah-kisah mereka tertuang dengan sangat menawan, dan dijamin mengajak Anda untuk ikut merasakan sensasi “beyond-traveling”. Ya, Anda bisa merasakan sendiri aroma interaksi dengan penduduk lokal, budaya setempat, maupun percakapan hati dengan aneka detail di setiap tempat.\n" +
                "\n" + "Nikmatilah buku ini, dan hiruplah dunia bersama kami, karena di setiap perjalanan ada cerita.\n","Fabiola Lawalata","Travel", 6);
        Book.books.add(newBook);

        newBook = new Book("9781855682977", "BANDUNG - SABANG PP", 90, "Bayangkan, seorang wanita berhijab seorang diri, mengendarai sepeda motor bebek, menempuh jarak ribuan kilometer. Tidak tanggung- tanggung, dari Bandung - titik nol kilometer di Sabang, nekad dijalani.\n" +
                "\n" +
                "Memang tidak ada yang bisa mengalahkan hasrat untuk bertualang. Sekali seumur hidup, segala batasan yang dianggap mustahil, perlu dijajal. Perjalanan menjelajah Sumatera memberikan banyak pengalaman dan kisah, dari yang manis hingga yang menegangkan. Beberapa kota menawarkan keindahan pemandangan, beberapa tempat memberikan keramahan. Wajah Sumatera yang seolah “garang” berhasil diakrabi.\n" +
                "\n" +
                "Catatan perjalanan dari Sheibasari ini akan membawa Anda pada keseruan tersendiri. Nikmati kisah-kisahnya yang mengasyikkan!\n", "Sheibasari", "Travel", 7);
        Book.books.add(newBook);

        //History
        newBook = new Book("9781855682978", "The History", 8, "Begini Ceritanya", "Siapa?", "History", 5);
        Book.books.add(newBook);

        newBook = new Book("9781855682989", "MyHistory", 3, "Begini Ceritanya", "Siapa?", "History", 3);
        Book.books.add(newBook);

        newBook = new Book("9781855682999","JudulHistory03", 21,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa?", "History", 7);
        Book.books.add(newBook);

        newBook = new Book("9781855682909", "JudulHistory04", 18,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa?", "History", 8);
        Book.books.add(newBook);

        newBook = new Book("9781855682919","JudulHistory05", 12,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa?", "History", 10);
        Book.books.add(newBook);

        // Religion
        newBook = new Book("9781855682929","Religion Clue", 1,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa1?", "Religion", 4);
        Book.books.add(newBook);

        newBook = new Book("9781855682939","JudulReligion02", 2,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa2?", "Religion", 5);
        Book.books.add(newBook);

        newBook = new Book("9781855682949","JudulReligion03", 3,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa3?", "Religion", 9);
        Book.books.add(newBook);

        newBook = new Book("9781855682959", "JudulReligion04", 4,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa4?", "Religion", 5);
        Book.books.add(newBook);

        newBook = new Book("9781855682969", "JudulReligion05", 7,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa5?", "Religion", 6);
        Book.books.add(newBook);

        newBook = new Book("9781855682079","JudulReligion06", 8,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa6?", "Religion", 8);
        Book.books.add(newBook);

        newBook = new Book("9781855682179", "JudulReligion07", 9,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy",
                "Siapa7?", "Religion", 10);
        Book.books.add(newBook);

        newBook = new Book("9781855682279","JudulReligion08", 10, "Cerita mengenai ini begini8", "Siapa8?", "Religion",3);
        Book.books.add(newBook);

        // Science
        newBook = new Book("9781855682379", "SearchTesting", 9, "Blablabla blaBla blablabla Blablablab laBlablablabl aBlablablablaB lablablabla"+
                "BlablablablaBla blablabla Blablablab laBlablab lablaBlablablab laBlablablabla"
                +"Blablablabla Blablablabla Blablablabla Blablablabla", "Siapa1?", "Science", 8);
        Book.books.add(newBook);

        newBook = new Book("9781855682479", "The Science", 3,
                "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy"+
                        "dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy dummy"
                +"Blablablabla Blablablabla Blablablabla Blablablabla", "Siapa2?", "Science", 7);
        Book.books.add(newBook);

        newBook = new Book("9781855682579","SBook", 15, "Blablabla blaBla blablabla Blablablab laBlablablabl aBlablablablaB lablablabla"+
                "BlablablablaBla blablabla Blablablab laBlablab lablaBlablablab laBlablablabla"
                +"Blablablabla Blablablabla Blablablabla Blablablabla", "Siapa3?", "Science", 9);
        Book.books.add(newBook);

        newBook = new Book("9781855682679","JudulScience04", 16, "Blablabla blaBla blablabla Blablablab laBlablablabl aBlablablablaB lablablabla"+
                "BlablablablaBla blablabla Blablablab laBlablab lablaBlablablab laBlablablabla"
                +"Blablablabla Blablablabla Blablablabla Blablablabla", "Siapa4?", "Science", 7);
        Book.books.add(newBook);

        newBook = new Book("9781855682779","The X", 18, "Blablabla blaBla blablabla Blablablab laBlablablabl aBlablablablaB lablablabla"+
                "BlablablablaBla blablabla Blablablab laBlablab lablaBlablablab laBlablablabla"
                +"Blablablabla Blablablabla Blablablabla Blablablabla", "Siapa5?", "Science", 10);
        Book.books.add(newBook);

    }

    private void setAlarm(){
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent;
        PendingIntent pendingIntent;
        myIntent = new Intent(this,alarmNotificationReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this,0,myIntent,0);

        // Set Waktu
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(System.currentTimeMillis());

//        time.set(Calendar.HOUR_OF_DAY, 12);
//        time.set(Calendar.MINUTE, 0);
//        time.set(Calendar.SECOND, 0);

        manager.setRepeating(AlarmManager.RTC_WAKEUP, time.getTimeInMillis(),1000*60,pendingIntent);
    }
}
