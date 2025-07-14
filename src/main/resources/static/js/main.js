let current = new Date();

const currentMonth = document.getElementById('currentMonth');
const grid = document.getElementById('calendarGrid');
const prevBtn = document.getElementById('prevMonth');
const nextBtn = document.getElementById('nextMonth');

function renderCalender(date){
    currentMonth.textContent = `${date.getFullYear()}년 ${date.getMonth() + 1}월`;

    grid.innerHTML = '';

    const firstDay = new Date(date.getFullYear(), date.getMonth(), 1);
    const lastDay = new Date(date.getFullYear(), date.getMonth() + 1 , 0);

    for(let i = 0; i < firstDay.getDay(); i++)
    {
        const blank = document.createElement('div');
        blank.className = 'py-3';      // ← 높이 확보
        grid.appendChild(blank);
    }

    const isSameMonth = date.getMonth() === current.getMonth() &&
        date.getFullYear() === current.getFullYear();

    for(let d = 1; d < lastDay.getDate(); d++)
    {
        const m = String(date.getMonth() + 1).padStart(2, '0');
        const day = String(d).padStart(2, '0');

        const dateStr = `${m}${day}`;
        const cell = document.createElement('a');
        cell.textContent = d;

        cell.classList.add(
            'text-center', 'py-3', 'rounded-lg',
            'cursor-pointer', 'hover:bg-primary/10',
            'select-none'
        );

        const isFuture = isSameMonth && d > current.getDate();
        if(isFuture)
        {
            cell.classList.add('text-gray-400');
        }
        else
        {
            cell.href = `/diary/${dateStr}`;
            cell.classList.add('text-gray-800');
        }


        grid.appendChild(cell);
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