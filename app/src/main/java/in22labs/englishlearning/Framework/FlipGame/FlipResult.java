package in22labs.englishlearning.Framework.FlipGame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import in22labs.englishlearning.Adapter.FlipQuesshow;
import in22labs.englishlearning.MainActivity;
import in22labs.englishlearning.R;

public class FlipResult extends AppCompatActivity {
    ArrayList<String > QuesLsit, Ch1List, Ch2List;
    String[] ArrQues, ArrCh1, ArrCh2;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flip_result);
        listView = (ListView)findViewById(R.id.list_results);
        QuesLsit = new ArrayList<>();
        QuesLsit.add("Buy her a present for her birthday. She lives by a park.");
        QuesLsit.add("There's a hole in my trousers. I read the whole book in one day.");
        QuesLsit.add("There is a bird in the garden. The children have done their homework.");
        QuesLsit.add("Write your name on top of the test paper. I couldn't remember the right answer.");
        QuesLsit.add("I went to the sea to see my friend.");
        Ch1List = new ArrayList<>();
        Ch1List.add("Buy");
        Ch1List.add("hole");
        Ch1List.add("their");
        Ch1List.add("Write");
        Ch1List.add("see");
        Ch2List = new ArrayList<>();
        Ch2List.add("by");
        Ch2List.add("whole");
        Ch2List.add("There");
        Ch2List.add("right");
        Ch2List.add("sea");

        ArrQues = new String[QuesLsit.size()];
        ArrQues = QuesLsit.toArray(ArrQues);

        ArrCh1 = new String[Ch1List.size()];
        ArrCh1 = Ch1List.toArray(ArrCh1);

        ArrCh2 = new String[Ch2List.size()];
        ArrCh2 = Ch2List.toArray(ArrCh2);
        listView.setAdapter( new FlipQuesshow(FlipResult.this, ArrQues, ArrCh1, ArrCh2));

    }

    public void onBackPressed() {
        Intent i = new Intent(FlipResult.this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
