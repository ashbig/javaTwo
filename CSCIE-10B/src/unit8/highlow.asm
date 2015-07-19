# highlow.asm -- prints the highest and lowest integers of a data set
#
# Ashkan Bigdeli, CSCIE10b unit8

## Registers used:
   # t0 loop counter
   # t1 address of length of elements
   # t2 loaded length of elements
   # t3 address of table
   # t4 highest element in table at given time
   # t5 used to point to the next element

.data
TABLE:    .word  3 -1 6 5 7 -3 -15 18 2
N:        .word  9
#TABLE:   .word  3
#N:       .word  1

newline:  .asciiz "\n"

.text


main:

        li   $t0, 0       # loop counter
        

        la   $t1, N       # address of number of elements, or times the loop runs
        lw   $t2  ($t1)   # load above into t2

        la   $t3, TABLE   # address of table elements
        lw   $t4 ($t3)    # load table into register t4

        
        addu $t3, $t3, 4  # t5 now contains second element in the array
        lw   $t5 ($t3)    # load the next element into t5
  
high_loop:
        beq  $t0, $t2, end    # if we are finished the loop, go to print this element
        bgt  $t5, $t4, higher  # branch to higher if the next element is larger

        
        addu $t3, $t3, 4  # move to the next element
        lw   $t5, ($t3)   # load the next element
        add $t0, $t0, 1   # increment the counter
        j high_loop       # loop

        higher:
        move $t4, $t5     # move the highest value to t4
        addu $t3, $t3, 4  # move the address of the next element
        lw $t5, ($t3)     # load the next element
        add $t0, $t0, 1   #increment the counter
        j high_loop       # loop 

end:
        move $a0, $t4     #move the highest number over to arg0 be printed.
        li   $v0, 1
        syscall

reset:        

        li   $t0, 0         # reset the loop counter

        la   $t3, TABLE     # address of table elements
        lw   $t4 ($t3)      # load table into register t4

        
        addu $t3, $t3, 4    # t3 now contains second element in the array
        lw   $t5 ($t3)      # load next element into t5

low_loop:
        beq  $t0, $t2, end2   # if we are finished the loop, reload for low_loop
        blt  $t5, $t4, lower  # branch to higher if the next element is lower

        
        addu  $t3, $t3, 4   # move to the next element
        lw    $t5, ($t3)    # load the next element
        add   $t0, $t0, 1   # increment the counter
        j     low_loop      # loop

        lower:
        move  $t4, $t5      # move the highest value to t4
        addu  $t3, $t3, 4   # move the address of the next element
        lw    $t5, ($t3)    # load the next element
        add   $t0, $t0, 1   # increment the counter
        j     low_loop      # loop 



end2:

        la   $a0, newline  # load new line to be printed
        li   $v0, 4
        syscall
 
        move $a0, $t4     # move the lowest number over to arg0 be printed.
        li   $v0, 1
        syscall

        li $v0, 10        # quit program
        syscall
#end highlow.asm
