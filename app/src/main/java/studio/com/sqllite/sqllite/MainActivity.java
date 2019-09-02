package studio.com.sqllite.sqllite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            //instanciando banco de dados
            SQLiteDatabase bancoDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //DROP
            //bancoDados.execSQL("DROP TABLE pessoas");

            //CREATE
            bancoDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(id INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, idade INT(3))");

            //INSERT
            bancoDados.execSQL( "INSERT INTO pessoas (nome, idade) values ('Juvenal', 33)");
            bancoDados.execSQL("INSERT INTO pessoas (nome, idade) values ('Rosana', 23)" );

            //UPDATE
            bancoDados.execSQL("UPDATE pessoas SET idade = 44 WHERE id = 1 " );

            //DELETE
            //bancoDados.execSQL("DELETE FROM pessoas WHERE nome = 'Tiago'" );


            //percorreer dados
            Cursor cursor = bancoDados.rawQuery( "SELECT * FROM pessoas", null ); //baseado na query um cursor é montado


            //recuperar indice coluna

            int indiceColunaNome = cursor.getColumnIndex("nome"); //coletando o indice da coluna nome
            int indiceColunaIdade = cursor.getColumnIndex("idade"); //coletando o indice da coluna idade
            int indiceColunaId = cursor.getColumnIndex("id");

            //fazendo o cursor retornar
            cursor.moveToFirst();

            //exibir os registros da tabela
            while (cursor != null) {
                Log.i("RESULTADO - id", cursor.getString(indiceColunaId));
                Log.i("RESULTADO - nome", cursor.getString(indiceColunaNome));
                Log.i("RESULTADO - idade", cursor.getString(indiceColunaIdade));

                cursor.moveToNext(); //cursor caminha
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
