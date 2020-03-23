python3 from powerline.vim import setup as powerline_setup
python3 powerline_setup()
python3 del powerline_setup

set laststatus=2
" Quickly insert an empty new line without entering insert mode
    nnoremap <Leader>o o<Esc>
    nnoremap <Leader>O O<Esc>
set number relativenumber
