package com.glc.itbook.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.glc.itbook.AddActivity;
import com.glc.itbook.AddCoachActivity;
import com.glc.itbook.AddLessonActivity;
import com.glc.itbook.AddPasswordActivity;
import com.glc.itbook.BookActivity;
import com.glc.itbook.CheckItemActivity;
import com.glc.itbook.CoachCheckLessonActivity;
import com.glc.itbook.CoachCheckMemberActivity;
import com.glc.itbook.GymInfoActivity;
import com.glc.itbook.LessonActivity;
import com.glc.itbook.R;
import com.glc.itbook.TimeActivity;
import com.glc.itbook.UpdateActity;
import com.glc.itbook.UpdateItemActivity;
import com.glc.itbook.UpdateLessonActivity;

public class Fragment_dongtai extends Fragment {
    private LinearLayout addBook;
    private LinearLayout addLesson;
    private LinearLayout currentBook;

    private LinearLayout updateBook;
    private LinearLayout updateLesson;
    private LinearLayout ly_addMember;
    private LinearLayout ly_addCoach;
    private LinearLayout ly_addLessonAdmin;
    private LinearLayout ly_checkEquipment;
    private LinearLayout ly_checkNum;
    private LinearLayout ly_deleteMember;
    private LinearLayout ly_deleteCoach;
    private LinearLayout ly_deleteComment;
    private LinearLayout ly_book;
    private LinearLayout password;

    private LinearLayout coachCheckLesson;
    private LinearLayout coachCheckMember;
    private String username;
    private String role;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        username = getArguments().getString("username");
        role = getArguments().getString("role");
        if(role.equals("Admin")) {
            return inflater.inflate(R.layout.fragment_dongtai_admin, null);
        }
        else if (role.equals("Coach")){
            return inflater.inflate(R.layout.fragment_dongtai_coach, null);
        }
        return inflater.inflate(R.layout.fragment_dongtai,null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        username = getArguments().getString("username");
        role = getArguments().getString("role");
        super.onViewCreated(view, savedInstanceState);
        if(role.equals("Admin")) {
            ly_addMember=view.findViewById(R.id.ly_addMember);
            ly_addCoach=view.findViewById(R.id.ly_addCoach);
            ly_addLessonAdmin=view.findViewById(R.id.ly_addLessonAdmin);
            ly_checkEquipment=view.findViewById(R.id.ly_checkEquipment);
            ly_checkNum=view.findViewById(R.id.ly_checkNum);
//            ly_deleteMember=view.findViewById(R.id.ly_deleteMember);
//            ly_deleteCoach=view.findViewById(R.id.ly_deleteCoach);
            password=view.findViewById(R.id.ly_password);
            ly_book=view.findViewById(R.id.ly_book);
            password.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), AddPasswordActivity.class));
                }
            });
            ly_addMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), TimeActivity.class));
                }
            });
            ly_addCoach.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), AddCoachActivity.class));
                }
            });
            ly_addLessonAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), AddLessonActivity.class));
                }
            });
            ly_checkEquipment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), CheckItemActivity.class));
                }
            });
            ly_checkNum.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), GymInfoActivity.class));
                }
            });
//            ly_deleteMember.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(), TimeActivity.class));
//                }
//            });
//            ly_deleteCoach.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    startActivity(new Intent(getActivity(), TimeActivity.class));
//                }
//            });
            ly_book.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), TimeActivity.class));
                }
            });
            /*功能一：发布新的课程，涵盖*/
        }
        else if (role.equals("Coach")){
            coachCheckLesson=view.findViewById(R.id.checkLesson);
            coachCheckMember=view.findViewById(R.id.checkMember);
            coachCheckLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), CoachCheckLessonActivity.class));
                }
            });
            coachCheckMember.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), CoachCheckMemberActivity.class));
                }
            });
        }
        else {
            currentBook=view.findViewById(R.id.ly_useStatus);
            addBook=view.findViewById(R.id.ly_addBook);
            addLesson=view.findViewById(R.id.ly_addLesson);
            updateBook=view.findViewById(R.id.ly_updateBook);
            updateLesson=view.findViewById(R.id.ly_updateLesson);


            currentBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), TimeActivity.class);
                    intent.putExtra("time", "now");
                    startActivity(intent);
                }
            });
            addBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), TimeActivity.class);
                    intent.putExtra("time", "future");
                    startActivity(intent);
                }
            });
            addLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), LessonActivity.class));
                }
            });
            updateBook.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(username.equals("admin")){
                        startActivity(new Intent(getActivity(), UpdateActity.class));

                    }else {
                        startActivity(new Intent(getActivity(), UpdateItemActivity.class));
//                    Toast.makeText(getActivity(), "对不起您未有该权限，请联系管理员", Toast.LENGTH_SHORT).show();


                    }
                }
            });
            updateLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), UpdateLessonActivity.class);
//                    intent.putExtra("time", "now");
                    startActivity(intent);
                }
            });
        }

//        ly_deleteComment.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if(username.equals("admin")){
//                    startActivity(new Intent(getActivity(), UpdateActity.class));
//
//                }else {
//                    startActivity(new Intent(getActivity(), UpdateActity.class));
////                    Toast.makeText(getActivity(), "对不起您未有该权限，请联系管理员", Toast.LENGTH_SHORT).show();
//
//
//                }
//            }
//        });
    }
}
