# perfect.asm -- prints any perfect number in a data set
#
# Ashkan Bigdeli, CSCIE10b unit8

## Registers used:
   # t0 represents the sum
   # t1 the first number to check
   # t2 the last number to check
   # t3 the number we are dividing by
   # t4 the remainder of our divisions

.data
newline:  .asciiz "\n"

.text

main:

        li    $t0, 0       # represents the sum

        li    $t1, 5       # first number to be tested
        li    $t2, 500     # last number to be tested
        

loop:
        beq   $t1, $t2, end  # if this is greater the last number to be test, end
        li    $t3, 1         # division sum

checkperfect:
        beq   $t1, $t3, add_sum   # move to check if sum is correct
        div   $t1, $t3            # division of number
        mfhi  $t4                 # move mips remainder of division storage to t4
        beqz  $t4, add_divisor    # remainder if is zero add it to the sum
        add   $t3, $t3, 1         # else load the next number to divide
        b checkperfect            # divide again

add_divisor:
        add $t0, $t0, $t3    # add the divisor to the current number number
        add $t3, $t3, 1      # incremenet the divisor 
        b checkperfect

print:

        move $a0, $t1       # print the perfect number
        li   $v0, 1 
        syscall

        la   $a0, newline   # print a new line
        li   $v0, 4
        syscall

	    li $t0, 0           # rest the sum
        add  $t1, $t1, 1    # move on the next number to check
        b loop				# itterate loop 

add_sum:
        beq $t0, $t1, print  # if the sum equals the number print
        li   $t0, 0          # else, reset the sum for next itteration
        add  $t1, $t1, 1     # move onto the next number to check
        b loop               # itterate loop

end:
    li   $v0, 10       # quit the program when done running last number
    syscall
#end perfect.asm
