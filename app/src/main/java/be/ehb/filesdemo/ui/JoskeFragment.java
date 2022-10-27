package be.ehb.filesdemo.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;

import be.ehb.filesdemo.R;

public class JoskeFragment extends Fragment {

    //verwijzingen naar mijn views
    private EditText etToSave, etSaved;
    private Button btnRead, btnWrite;

    private final String FILENAME = "jaqueline.banaan";

    //listeners
    private View.OnClickListener readListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                FileInputStream mFileInputStream = getContext().openFileInput(FILENAME);
                InputStreamReader streamReader = new InputStreamReader(mFileInputStream);

                char[] gelezenBytes = new char[5000];
                streamReader.read(gelezenBytes, 0, 5000);
                String resultString = new String(gelezenBytes);
                etSaved.setText(resultString);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    };

    private View.OnClickListener writeListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                FileOutputStream mFileOutputStream = getContext().openFileOutput(FILENAME, Context.MODE_PRIVATE);
                OutputStreamWriter streamWriter = new OutputStreamWriter(mFileOutputStream);

                streamWriter.write(etToSave.getText().toString());
                streamWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    };

    public JoskeFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View mView = inflater.inflate(R.layout.fragment_joske, container, false);

        etToSave = mView.findViewById(R.id.et_to_save);
        etSaved = mView.findViewById(R.id.et_saved);
        btnRead = mView.findViewById(R.id.btn_read);
        btnWrite = mView.findViewById(R.id.btn_write);

        btnRead.setOnClickListener(readListener);
        btnWrite.setOnClickListener(writeListener);

        return mView;
    }
}
