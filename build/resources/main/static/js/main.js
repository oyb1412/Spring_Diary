let current = new Date();

const currentMonth = document.getElementById('currentMonth');
const grid = document.getElementById('calendarGrid');
const prevBtn = document.getElementById('prevMonth');
const nextBtn = document.getElementById('nextMonth');

function renderCalender(date) {
    currentMonth.textContent = `${date.getFullYear()}년 ${date.getMonth() + 1}월`;
    grid.innerHTML = '';

    const firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    const lastDay  = new Date(date.getFullYear(), date.getMonth() + 1, 0);

    // 앞쪽 빈칸
    for (let i = 0; i < firstDay.getDay(); i++) {
        const blank = document.createElement('div');
        blank.className = 'py-3';
        grid.appendChild(blank);
    }

    const isSameMonth = date.getMonth() === current.getMonth() &&
        date.getFullYear() === current.getFullYear();

    for (let d = 1; d <= lastDay.getDate(); d++) {          // ← <= 로 수정(마지막 날 포함)
        const m       = String(date.getMonth() + 1).padStart(2, '0');
        const day     = String(d).padStart(2, '0');
        const dateStr = `${m}${day}`;                         // ex) "0705"

        // 셀 래퍼 (flex로 체크랑 숫자 나란히)
        const wrapper = document.createElement('div');
        wrapper.classList.add('flex', 'items-center', 'justify-center', 'gap-1');

        // 날짜 텍스트 (a 태그 그대로)
        const cell = document.createElement('a');
        cell.textContent = d;
        cell.classList.add(
            'text-center', 'py-3', 'rounded-lg',
            'cursor-pointer', 'hover:bg-primary/10',
            'select-none', 'w-8', 'h-8', 'flex', 'items-center', 'justify-center'
        );

        const isFuture = isSameMonth && d > current.getDate();
        if (isFuture) {
            cell.classList.add('text-gray-400');
        } else {
            cell.href = `/diary/${dateStr}`;
            cell.classList.add('text-gray-800');
        }


        const url = `/api/diary/check/${dateStr}`;

        $.ajax({
            type : 'GET',
            url : url,
        }).done(function(result){
            if(result.check)
            {
                const mark = document.createElement('span');
                mark.textContent = '✔';
                mark.classList.add('text-red-500', 'text-xs');
                wrapper.appendChild(mark);
            }
        }).fail(function(error){
            alert(`서버 오류 발생 : ${error}`);
        }).always(function(){
            buttonOnEnable(btn, false);
        })

        wrapper.appendChild(cell);
        grid.appendChild(wrapper);
    }
}

renderCalender(current);

prevBtn.addEventListener('click', () => {
    current.setMonth(current.getMonth() - 1);
    renderCalender(current);
});

nextBtn.addEventListener('click', () => {
    current.setMonth(current.getMonth() + 1);
    renderCalender(current);
});