export interface IRoute {
  id: string;
  agency_id?: string | null;
  route_short_name?: string | null;
  route_long_name?: string | null;
  route_desc?: string | null;
  route_type?: string | null;
}

export type NewRoute = Omit<IRoute, 'id'> & { id: null };
