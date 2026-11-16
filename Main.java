 class MySemaphore { 


   private int permits; 
    
    public MySemaphore(int permits) 
    
    {        this.permits = permits;    }  
    
    
      public synchronized void acquire() { 
        
        
       while (permits == 0) {  

            try {   wait();   } 
                             
    catch (InterruptedException e) {          }
 
       } 
        permits--;  
        }
               
                  
       public synchronized void release() { 
                
        permits++;       
        notifyAll();    } 
               } 
class Depot extends Thread {

private int id ;


public Depot(int id){
    this.id = id ;
}

public void run() {

for(int j =1 ; j <= 10 ; j++){


Main.mutex.acquire() ;

int value = id * 200 + j ;

Main.slots[Main.next] = value ;

try{ Thread.sleep(1);}

catch(InterruptedException e ) {  } 

Main.next++ ;

Main.mutex.release() ;

}

}
 }
 public class Main {
  static int[] slots = new int[30] ;
  static int next = 0 ;
   static MySemaphore mutex = new MySemaphore(1) ;


    public static void main(String[] args) throws InterruptedException{
       
       Depot d1 = new Depot(1) ;
       Depot d2 = new Depot(2) ;
       Depot d3 = new Depot(3) ;

        d1.start();
        d2.start();
        d3.start();

        d1.join();
        d2.join();
        d3.join();
          System.out.println("Slots filled:" + next);
int sum = 0; int EmptySlots = 0 ;
for(int i =0 ; i <30 ;i++)
    { sum+= slots[i] ;
       if(slots[i] == 0) 
       {EmptySlots++ ;} 
    }

          System.out.println("Sum of all values:" +sum);
          System.out.println("Empty slots:" +EmptySlots);
    }
}