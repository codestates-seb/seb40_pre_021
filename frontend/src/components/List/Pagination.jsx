import { useEffect } from 'react';
import styled from 'styled-components';

const PaginationStyle = styled.div`
	border-top: 1px solid rgb(209, 211, 215);
	margin-bottom: 48px;
	padding: 36px;
	display: flex;
	justify-content: space-between;

	button {
		margin: 2px;
	}
	.active {
		background: rgb(229, 136, 62);
		color: white;
		border: none;
	}
	.pages {
		display: flex;

		div {
			display: flex;
			align-items: flex-end;
		}
	}

	.pagesize {
		display: flex;
		align-items: center;

		div {
			margin: 0 2px;
		}
	}
`;
const Pagination = ({
	pages,
	createPageButton,
	now,
	calTotalpage,
	pageSize,
}) => {
	const pagesizeList = ['15', '30', '50'];
	useEffect(() => {
		calTotalpage();
	}, [pageSize]);

	return (
		<PaginationStyle>
			<div className="pages">
				{pages.map((page) => {
					if (now < 4 && page <= 5) {
						return createPageButton(page, 'page');
					} else {
						if (
							page === 1 ||
							(page >= now - 2 && page <= now + 2) ||
							page === pages.length
						) {
							return (
								<div key={page}>
									{page === pages.length && now < page - 2 && `...`}
									{createPageButton(page, 'page')}
									{page === 1 && now > page + 2 && `...`}
								</div>
							);
						}
					}
				})}
			</div>
			<div className="pagesize">
				{pagesizeList.map((size) => {
					return <div key={size}>{createPageButton(size, 'pageSize')}</div>;
				})}
				<div>per page</div>
			</div>
		</PaginationStyle>
	);
};

export default Pagination;
