# palindrome.asm -- reads a line of text and tests whether it is a palindrome.
## Register usage:
##	$t1	- A.
##	$t2	- B.
##	$t3	- the character *A.
##	$t4	- the character *B.
##	$v0	- syscall parameter / return values.
##	$a0	- syscall parameters.
##	$a1	- syscall parameters.

	.globl  test_loop
        .globl  length_loop
        .globl  string_space
	    .text
main:				           # SPIM starts by jumping to main.
					   # read the string S:
	la      $a0, string_space
	li      $a1, 1024
	li      $v0, 8	                   # load "read_string" code into $v0.
	syscall	

	la      $t1, string_space	   # initialize $t1 to point to the start

	la      $t2, string_space          # we need to move B to the end
length_loop:			           #	of the string:
	lb	$t3, ($t2)	           # load the byte *B into $t3.
	beqz	$t3, end_length_loop       # if $t3 == 0, branch out of loop.
	addu	$t2, $t2, 1	           # otherwise, increment B,
	b	length_loop		   #  and repeat the loop.
end_length_loop:
	subu	$t2, $t2, 2	           # subtract 2 to move B back past
				           #  the '\0' and '\n'.
test_loop:
	bge     $t1, $t2, is_palin	   # if A >= B, it is a palindrome.

	lb      $t3, ($t1)                 # load the byte *A into $t3,
	lb      $t4, ($t2)	           # load the byte *B into $t4.


    # BELOW IS EDITED BY ASHKAN BIGDELI
    sub     $t3, $t3, 0             # convert byte *A letter to ascii
    ble     $t3, 65, increment    # if it is less than ascii code 65 increment
    bge     $t3, 91, increment    # if it is greater than than ascii code 91 increment
    ble     $t3, 96, increment    # if it is greater than ascii code 97 incremenet
    ble     $t3,  122 increment    # if it is less than or equal to 122 incremenet


    sub     $t4, $t4, 0             # convert byte *B letter to ascii
    ble     $t4, 65, decrement    # if it is less than ascii code 65 decrement
    bge     $t4, 91, decrement    # if it is greater than than ascii code 91 decrement
    ble     $t4, 96, decrement    # if it is greater than ascii code 97 incremenet
    ble     $t4, 122, decrement    # if it is less than or equal to 122 incremenet

    increment:
    addu   $t1, $t1, 1            # incremenet A, thus skipping the non char 
    j test_loop

    decrement:
    addu $t2, $t2, 1              # decerment B, thus skipping the non char
    j test_loop
 
    # END EDIT
	bne     $t3, $t4, not_palin	   # if $t3 != $t4, not a palindrome.
								   # Otherwise,
	addu	$t1, $t1, 1                #  increment A,
	subu	$t2, $t2, 1                #  decrement B,
	b	test_loop                  #  and repeat the loop.

is_palin:	                           # print the is_palin_msg, and exit.
	la         $a0, is_palin_msg
	li         $v0, 4
	syscall
	b          exit

not_palin:
	la         $a0, not_palin_msg	  # print the is_palin_msg, and exit.
	li         $v0, 4
	syscall

exit:			                  # exit the program:
	li		$v0, 10	          # load "exit" into $v0.
	syscall			          # make the system call.

## Here is where the data for this program is stored:
	.data
string_space:	.space	1024  	# set aside 1024 bytes for the string.
is_palin_msg:	.asciiz "The string is a palindrome.\n"
not_palin_msg:	.asciiz "The string is not a palindrome.\n"
## end of palindrome.asm
