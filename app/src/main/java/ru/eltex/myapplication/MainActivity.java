package ru.eltex.myapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class MyThread implements Runnable{
    @Override
    public void run(){
        System.out.print("Ошибка");
    }
}
public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       button = (Button)findViewById(R.id.button);
       textView = (TextView) findViewById(R.id.button1);

        final float[] i = {100};
        final float[] q = {100};
        float x;
        float y;
        float x2;
        float y2;
        float vector_x;
        float vector_y;
        float bx = button.getX();
        //textView.setText((toString(bx));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Runnable task = () -> {
                    for (int k = 0; k < 100; k++) {
                        try {
                            Thread.sleep(10);
                       } catch (InterruptedException ie) {
                            System.out.println();
                        }
                        button.setTranslationX(i[0]);
                        i[0] += 2;
                    }

            };
                Thread thread = new Thread(task);
                thread.start();
                task = () -> {
                    for (int k = 0; k < 100; k++) {
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException ie) {
                            System.out.println();
                        }
                        button.setTranslationX(i[0]);
                        i[0] -= 2;
                        button.setTranslationY(q[0]);
                        q[0] += 2;
                    }
                };
                Thread thread1 = new Thread(task);
                try {
                    thread1.join();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                thread1.start();

            }
        });
    }
}