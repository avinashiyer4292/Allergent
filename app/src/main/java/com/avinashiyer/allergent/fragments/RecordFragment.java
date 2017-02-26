package com.avinashiyer.allergent.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.avinashiyer.allergent.ProfileActivity;
import com.avinashiyer.allergent.R;
import com.avinashiyer.allergent.ResultsActivity;
import com.google.gson.Gson;
import com.microsoft.projectoxford.vision.VisionServiceClient;
import com.microsoft.projectoxford.vision.VisionServiceRestClient;
import com.microsoft.projectoxford.vision.contract.LanguageCodes;
import com.microsoft.projectoxford.vision.contract.Line;
import com.microsoft.projectoxford.vision.contract.OCR;
import com.microsoft.projectoxford.vision.contract.Region;
import com.microsoft.projectoxford.vision.contract.Word;
import com.microsoft.projectoxford.vision.rest.VisionServiceException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecordFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecordFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ImageView imgView;
    private FloatingActionButton fab;
    private int RESULT_OK=1;
    private VisionServiceClient client;
    private ArrayList<String> wordsList;
    public RecordFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecordFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecordFragment newInstance(String param1, String param2) {
        RecordFragment fragment = new RecordFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_record, container, false);
        fab = (FloatingActionButton)v.findViewById(R.id.camera_button);
        imgView = (ImageView)v.findViewById(R.id.recordedImage);
        fab.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        return v;
    }

    private void takePicture(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //file = Uri.fromFile(getOutputMediaFile());
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Log.d("Request code","100");

                Bitmap mphoto = (Bitmap) data.getExtras().get("data");
                Log.d("Result code OK",""+mphoto.getByteCount());
                //imgView.setImageBitmap(mphoto);
                String myBase64Image = encodeToBase64(mphoto, Bitmap.CompressFormat.JPEG, 100);
                performOCR(mphoto);


        }
    }
    private void performOCR(Bitmap bitmap){
        Log.d("Performing OCR","DASDFDS");
        if (client==null){
            client = new VisionServiceRestClient(getString(R.string.subscription_key));
        }
        doRecognize(bitmap);
    }

    public void doRecognize(Bitmap bitmap) {
        Log.d("In do recognizing","adfad");
        try {
            new doRequest(bitmap).execute();
        } catch (Exception e)
        {
            //mEditText.setText("Error encountered. Exception is: " + e.toString());
        }
    }

    private String process(Bitmap bitmap) throws VisionServiceException, IOException {
        Gson gson = new Gson();
        Log.d("in process method","sdfdssdvds");
        // Put the image into an input stream for detection.
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());

        OCR ocr;
        ocr = this.client.recognizeText(inputStream, LanguageCodes.AutoDetect, true);

        String result = gson.toJson(ocr);
        Log.d("result ocr conversion", "Result is: "+result);

        return result;
    }
    private class doRequest extends AsyncTask<Bitmap, String, String> {
        // Store error message
        private Exception e = null;
        Bitmap bitmap;
        public doRequest(Bitmap bitmap) {
            this.bitmap = bitmap;
        }

        @Override
        protected String doInBackground(Bitmap... args) {
            Log.d("doing in background",""+bitmap.getByteCount());
            try {
                Log.d("Inside try","Sdfdsfdsf");
                return process(bitmap);
            } catch (Exception e) {
                this.e = e;    // Store error
                Log.d("exception",e.toString());
            }

            return null;
        }

        @Override
        protected void onPostExecute(String data) {
            super.onPostExecute(data);
            // Display based on error existence
            wordsList = new ArrayList<>();
            if (e != null) {

                this.e = null;
            } else {
                Gson gson = new Gson();
                OCR r = gson.fromJson(data, OCR.class);

                String result = "";
                for (Region reg : r.regions) {
                    for (Line line : reg.lines) {
                        for (Word word : line.words) {
                            //result += word.text + " ";
                            wordsList.add(word.text);
                            Log.d("Word is: ",word.text);
                        }
                        //result += "\n";
                    }
                    //result += "\n\n";
                }



            }
            Intent i = new Intent(getActivity(),ResultsActivity.class);
            i.putStringArrayListExtra("words",wordsList);
            startActivity(i);

        }
    }
    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality)
    {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }

    public static Bitmap decodeBase64(String input)
    {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }
}
