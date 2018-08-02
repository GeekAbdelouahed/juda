package com.academyatinfo.juda.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.academyatinfo.juda.R;
import com.academyatinfo.juda.utils.Constants;
import com.academyatinfo.juda.utils.Painter;
import com.academyatinfo.juda.BaseActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CertificationActivity extends BaseActivity {

    @BindView(R.id.image_certificate)
    ImageView imgCertificate;

    private File pictureFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_certification);

        ButterKnife.bind(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent intent = getIntent();

        String userName = intent.getStringExtra(Constants.KEY_NAME);
        String familyName = intent.getStringExtra(Constants.KEY_FAMILY_NAME);
        String degree = intent.getStringExtra(Constants.KEY_DEGREE);
        String gender = intent.getStringExtra(Constants.KEY_GENDER);
        String date = intent.getStringExtra(Constants.KEY_DATE);

        try {
            String path = "images/certificate.png";
            Bitmap bitmap = BitmapFactory.decodeStream(getAssets().open(path));
            bitmap = Painter.paint(this, bitmap, userName, familyName, degree, gender, date);
            storeImage(bitmap);
            imgCertificate.setImageBitmap(bitmap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void storeImage(Bitmap image) {
        pictureFile = getOutputMediaFile();
        try {
            FileOutputStream fos = new FileOutputStream(pictureFile);
            image.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                final Intent scanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                final Uri contentUri = Uri.fromFile(pictureFile);
                scanIntent.setData(contentUri);
                sendBroadcast(scanIntent);
            } else {
                final Intent intent = new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory()));
                sendBroadcast(intent);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/" + getString(R.string.directory) + "/");

        if (!mediaStorageDir.exists()) {
            mediaStorageDir.mkdir();
        }
        // Create a media file tvName
        String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm", Locale.getDefault()).format(new Date());
        String mImageName = "certificate_" + timeStamp + ".jpg";
        return new File(mediaStorageDir.getPath() + File.separator + mImageName);

    }

    public void shareCertification(View view) {
        Intent shareIntent = new Intent();
        Uri uri = Uri.fromFile(pictureFile);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_certificate)));

    }

    public void clickFinish(View view) {
        finish();
    }
}
