#include <stdio.h>
#include <mpi.h>

int main(int argc, char** argv) {
    int rank, size;
    int arr[10] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    int sum = 0;
    MPI_Init(&argc, &argv);
    MPI_Comm_rank(MPI_COMM_WORLD, &rank);
    MPI_Comm_size(MPI_COMM_WORLD, &size);
    int n = sizeof(arr) / sizeof(arr[0]);
    int local_sum = 0;
    for (int i = rank; i < n; i += size) {
        local_sum += arr[i];
    }
    MPI_Reduce(&local_sum, &sum, 1, MPI_INT, MPI_SUM, 0,
        MPI_COMM_WORLD);
    if (rank == 0) {
        printf("Sum of array elements is %d\n", sum);
    }
    MPI_Finalize();
}



/*
1.Install the Microsoft MPI package on your system.
sudo apt install build-essential
2.Install the C/C++ extension for Visual Studio Code.
3.Create a new C file and write your MPI program in it.
4.Open a terminal window 
5.Then Compile the Program using commands
 mpicc Program.c -oProgram
 mpirun -np 4 ./Program
 
 */
