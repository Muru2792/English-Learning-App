package in22labs.englishlearning.HomeTab;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import in22labs.englishlearning.Framework.Testmodule.Testmodule;
import in22labs.englishlearning.R;
import in22labs.englishlearning.Utils.SessionManager;

/**
 * Created by Murugan on 7/29/2015.
 */
public class ProfileHome extends Fragment {
    LinearLayout testStart;
    SessionManager session;
    ImageView im_profile, im_prof_update;
    Bitmap bm;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_profile,null);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        testStart=(LinearLayout)getActivity().findViewById(R.id.testAccess);
        session=new SessionManager(getActivity());
        im_profile=(ImageView)getActivity().findViewById(R.id.user_profile_photo);
        im_prof_update=(ImageView)getActivity().findViewById(R.id.user_profile_update);
        getPhotoRouund();

        testStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), Testmodule.class);
                startActivity(i);
                session.setFragpo(0);
            }
        });
    }

    private void getPhotoRouund() {

        File file = new File(Environment.getExternalStorageDirectory()+ File.separator + "English App/Photo/Photo.jpg");

        if(file.exists()){

            bm = BitmapFactory.decodeFile(file.getAbsolutePath());
            im_prof_update.setBackground(new BitmapDrawable(getCircleBitmap(bm)));
            im_prof_update.setVisibility(View.VISIBLE);
            im_profile.setVisibility(View.INVISIBLE);
        }else{
            bm = BitmapFactory.decodeResource(getResources(), R.drawable.avatar);
            im_profile.setImageBitmap(bm);
            im_prof_update.setVisibility(View.INVISIBLE);
            im_profile.setVisibility(View.VISIBLE);
        }
    }
    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);


        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);

        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();


        return output;
    }

}
