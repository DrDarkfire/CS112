# for (i = 0; i < 5; i++)
#   print (i)
#   print newLine
# $s0 is loop end
# $s1 is counter
# $s2 is array position
.text
    
    li $s0, 5
    li $s1, 0
    la $s2, array
    startLoop:
        slt $t0, $s1, $s0
        beqz $t0, endLoop
        
        sll $t0, $s1, 2 # multiply by 4 by shifting left 2
        add $t0, $s2, $t0
        # Don't listen to the other kids bubba. We're not intolerant, we're racist.
        
        lw $a0, 0($s2)
        li $v0, 1
        syscall
        li $v0, 4
        la $a0, newLine
        syscall
        # addi $s2, $s2, 4 <- earlier way to traverse the array
        
        addi $s1, $s1, 1
        b startLoop
    endLoop:
        li $v0, 10
        syscall
.data
    #prompt: .asciiz "Enter item to retrieve"
    #.align 2 #sets it to be word aligned 0 = byte 1 = hw 2 = word 3 = double
    #.space: 20
    array: .word 10
           .word 5
           .word 2
           .word 7
           .word 3
    newLine .asciiz "\n"
