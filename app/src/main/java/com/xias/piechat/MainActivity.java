package com.xias.piechat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.xias.piechat.entry.PieChatEntry;
import com.xias.piechat.view.PieChatView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PieChatView pieChatView;
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636, 0xFF800000, 0xFF808000, 0xFFFF8C69, 0xFF808080,
            0xFFE6B800, 0xFF7CFC00};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChatView = (PieChatView) findViewById(R.id.pie_chat_view);
        List<PieChatEntry> list = new ArrayList<>();
        PieChatEntry pie1 = new PieChatEntry("Pie1",mColors[0],true,0,30);
        PieChatEntry pie3 = new PieChatEntry("Pie2",mColors[1],false,69,102);
        PieChatEntry pie2 = new PieChatEntry("Pie3",mColors[2],false,30,69);
        PieChatEntry pie4 = new PieChatEntry("Pie4",mColors[3],false,102,131);
        PieChatEntry pie5 = new PieChatEntry("Pie5",mColors[4],false,131,173);
        PieChatEntry pie6 = new PieChatEntry("Pie6",mColors[5],false,173,206);
        PieChatEntry pie7 = new PieChatEntry("Pie7",mColors[6],false,206,282);
        PieChatEntry pie8 = new PieChatEntry("Pie8",mColors[7],false,282,303);
        PieChatEntry pie9 = new PieChatEntry("Pie9",mColors[8],false,303,360);
        list.add(pie1);
        list.add(pie2);
        list.add(pie3);
        list.add(pie4);
        list.add(pie5);
        list.add(pie6);
        list.add(pie7);
        list.add(pie8);
        list.add(pie9);
        pieChatView.setData(list);
    }
}
