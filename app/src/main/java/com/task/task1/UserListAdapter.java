package com.task.task1;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Delete;


import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

   private Context context;
   private List<UserEntity> userEntityList;
   UserDatabase userDatabase;

    List<UserEntity> list;
    List <UserEntity> tempList;
   public UserListAdapter(Context context,List<UserEntity> list) {
       this.context = context;
       this.list=list;
       tempList=list;
       userDatabase=UserDatabase.getInstance(context);
   }
    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent, false);
        return new MyViewHolder(view);
    }
    @Override
    public void onBindViewHolder( UserListAdapter.MyViewHolder holder, int position) {
        UserEntity ob=list.get(position);
        holder.name.setText(ob.getName1());
        holder.email.setText(ob.getEmail());
        holder.password.setText(ob.getPassword());

    }
   public void filterList(List<UserEntity> filterList){
       list= filterList;
       notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView name,email, password;
        EditText searchBar;
        ImageView profile;
        ImageButton edit,delete;



        public  MyViewHolder(View view){
            super(view);
            name =view.findViewById(R.id.tvName);
            email =view.findViewById(R.id.tvEmail);
            profile=view.findViewById(R.id.iVProfile);
            password =view.findViewById(R.id.tvPassword);
            edit = view.findViewById(R.id.btnEdit);
            delete =view.findViewById(R.id.btnDelete);
            searchBar= view.findViewById(R.id.search_bar);

          delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  int no = getAdapterPosition();
                  UserEntity ob = list.get(no);
                 int idx=ob.getId();
                  new Thread(new Runnable() {
                      @Override
                      public void run() {
                         UserEntity ob1 =userDatabase.getDao().getUser(idx);
                         userDatabase.getDao().deleteUser(ob1);
                        new Handler(Looper.getMainLooper()).post(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(context, "Deleted Successfully", Toast.LENGTH_SHORT).show();
                            }
                        });

                      }
                  }).start();
              }
          });

         edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int no= getAdapterPosition();
                    UserEntity ob= list.get(no);
                    int idx=ob.getId();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            UserEntity ob1= userDatabase.getDao().getUser(idx);
                            ob1.setPassword(ob1.password);
                            ob1.setName1(ob1.name1);
                            ob1.setEmail(ob1.email);
                            userDatabase.getDao().updateUser(ob1);
                        }
                    }).start();
                }
            });
        }
    }
}
