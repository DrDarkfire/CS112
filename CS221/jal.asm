.text
    la $a0, prompt
    jal PromptInt
    move $s0, $v0
    
    li $v0, 4
    la $a0, output
    syscall
    
    jal NewLine
    
    li $v0, 1
    move $a0, $s0
    syscall
    
    jal NewLine
    
    la $a0, string
    jal PrintString
    jal NewLine
    
    j Exit 
    #this will skip the lines that would be placed after it and go to the subprogram called Exit

.data
prompt: .asciiz "Enter a number"
output: .asciiz "You entered"
string: .asciiz "This is a string"

.text
Exit:
    li $v0, 10
    syscall

.text NewLine:
    li $v0, 4
    la $a0 newLine
    syscall
    jr $ra
.data
newLine: .asciiz "\n"

.text
# $a0 is the prompt
PromptInt:
    li $v0, 4
    syscall
    
    li $v0, 5
    syscall
    jr $ra

.text
# $a0 is the string to print
PrintString:
    li $v0, 4
    syscall
    sw $ra, PrintString_ra
    jal NewLine
    lw $ra, PrintString_ra
    jr $ra
.data
    PrintString_ra: .word
