package com.task.task1;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.MyViewHolder> {

   private Context context;
   private List<UserEntity> userEntityList;
   UserDatabase userDatabase;

    List<UserEntity> list;
    List <UserEntity>templist;
   public UserListAdapter(Context context,List<UserEntity> list) {
       this.context = context;
       this.list=list;
       templist=list;
       userDatabase=UserDatabase.getInstance(context);
   }


    @Override
    public UserListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row,parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder( UserListAdapter.MyViewHolder holder, int position) {
         // holder.tvName.setText(this.userEntityList.get(position).name);
        UserEntity ob=list.get(position);
        holder.Name.setText(ob.getName1());
        holder.Email.setText(ob.getEmail());
        holder.Password.setText(ob.getPassword());




    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView Name,Email, Password;
      ImageButton Edit,Delete;


        public  MyViewHolder(View view){
            super(view);
            Name =view.findViewById(R.id.tvName);
            Email =view.findViewById(R.id.tvEmail);
            Password =view.findViewById(R.id.tvPassword);
            Edit = view.findViewById(R.id.btnEdit);
            Delete =view.findViewById(R.id.btnDelete);

            
          Delete.setOnClickListener(new View.OnClickListener() {
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
         /*   btn.OnClickListener
            {
            int no=getAdapterpostion();
         Userentity ob=   list.get(no);
        thred{
      // Usertntiy ob1= usedatabase.getdao.getuser(ob.getid());
             usedatabase.getdao.deleteuser(ob1);

        }

            }*/
         Edit.setOnClickListener(new View.OnClickListener() {
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

            /*
            btn.setonclic{
             int no=getAdapterpostion();
         Userentity ob=   list.get(no);
        thred{
      // Usertntiy ob1= usedatabase.getdao.getuser(ob.getid());
            ob1.setPassword
            ob1.setName
            ob1.setEmail
            userdatabase.getdao.updateuser(ob1)



            }
            */




        }
    }
}
